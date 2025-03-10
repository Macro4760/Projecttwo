package data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.MatchDto;
import data.dto.SummonerDto;
import data.mapper.SummonerMapper;

@Service
public class SummonerService {
	@Autowired
    private SummonerMapper summonerMapper;

    public SummonerDto getSummonerInfo(String summonerName) {
        return summonerMapper.getSummonerInfo(summonerName);
    }

    public List<MatchDto> getRecentMatches(String summonerId) {
        return summonerMapper.getRecentMatches(summonerId);  // MatchDto 리스트 반환
    }

    public MatchDto getMatchDetails(String matchId) {
        return summonerMapper.getMatchDetails(matchId);
    }

    public double getWinRate(String summonerId) {
        return summonerMapper.getWinRate(summonerId);
    }
}
