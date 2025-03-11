package data.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import data.dto.MatchDto;
import data.dto.SummonerDto;
import data.mapper.SummonerMapper;

@Service
public class SummonerService {

    private static final String SUMMONER_API_URL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
    private static final String MATCH_API_URL = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/";
    
    @Value("${riot.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    private SummonerMapper summonerMapper;

    public SummonerDto getSummonerInfo(String summonerName) {
        String url = SUMMONER_API_URL + summonerName + "?api_key=" + apiKey;
        try {
            return restTemplate.getForObject(url, SummonerDto.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("API 호출 실패: " + e.getMessage());
        }
    }

    public List<MatchDto> getRecentMatches(String puuid) {
        List<String> matchIds = getMatchList(puuid);
        return matchIds.stream()
                .map(this::getMatchDetails)
                .collect(Collectors.toList());
    }

    public List<String> getMatchList(String puuid) {
        String url = MATCH_API_URL + puuid + "/ids?start=0&count=10&api_key=" + apiKey;
        try {
            return restTemplate.getForObject(url, List.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("API 호출 실패: " + e.getMessage());
        }
    }

    public MatchDto getMatchDetails(String matchId) {
        String url = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;
        try {
            return restTemplate.getForObject(url, MatchDto.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("API 호출 실패: " + e.getMessage());
        }
    }

    public double getWinRate(String summonerId) {
        return summonerMapper.getWinRate(summonerId);
    }
}
