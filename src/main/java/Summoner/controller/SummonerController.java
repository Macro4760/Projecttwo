package Summoner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dto.MatchDto;
import data.dto.SummonerDto;
import data.service.SummonerService;

@Controller
public class SummonerController {
	@Autowired
    private SummonerService summonerService;

	@GetMapping("/summoner")
	public String getSummonerInfo(@RequestParam String summonerName, Model model) {
	    // 소환사 정보 가져오기
	    SummonerDto summoner = summonerService.getSummonerInfo(summonerName);
	    model.addAttribute("summoner", summoner);

	    // 최근 게임 기록 가져오기
	    List<MatchDto> recentMatches = summonerService.getRecentMatches(summoner.getSummonerId());
	    model.addAttribute("recentMatches", recentMatches);

	    // 승률 가져오기
	    double winRate = summonerService.getWinRate(summoner.getSummonerId());
	    model.addAttribute("winRate", winRate);

	    return "summonerDetail";
	}

	@GetMapping("/matchDetails")
	public String getMatchDetails(@RequestParam String matchId, Model model) {
	    MatchDto match = summonerService.getMatchDetails(matchId);
	    model.addAttribute("match", match);

	    return "matchDetail";
	}

}
