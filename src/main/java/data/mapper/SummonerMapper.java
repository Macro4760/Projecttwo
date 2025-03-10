package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.MatchDto;
import data.dto.SummonerDto;

@Mapper
public interface SummonerMapper {
	public SummonerDto getSummonerInfo(String summonerName);

	public List<MatchDto> getRecentMatches(String puuid);

	public MatchDto getMatchDetails(String matchId);

	public double getWinRate(String summonerId);
}
