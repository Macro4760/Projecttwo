package data.service;

import java.util.List;

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

    private static final String API_URL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/";

    @Value("${riot.api.key}")
    private String apiKey;

    @Autowired
    private SummonerMapper summonerMapper;

    public SummonerDto getSummonerInfo(String summonerName) {
        return summonerMapper.getSummonerInfo(summonerName);
    }

    public List<MatchDto> getRecentMatches(String puuid) {
        return summonerMapper.getRecentMatches(puuid);
    }

    public MatchDto getMatchDetails(String matchId) {
        return summonerMapper.getMatchDetails(matchId);
    }

    public double getWinRate(String summonerId) {
        return summonerMapper.getWinRate(summonerId);
    }
}

