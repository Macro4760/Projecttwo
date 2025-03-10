package Summoner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dto.MatchDto;
import data.dto.SummonerDto;
import data.service.SummonerService;

@Controller
@RequestMapping("/summoner")
public class SummonerController {
    @Autowired
    private SummonerService summonerService;

    @GetMapping("/{summonerName}")
    public String getSummonerInfo(@PathVariable String summonerName, Model model) {
        SummonerDto summoner = summonerService.getSummonerInfo(summonerName);
        if (summoner == null) {
            model.addAttribute("error", "소환사를 찾을 수 없습니다.");
            return "errorPage";
        }
        model.addAttribute("summoner", summoner);

        List<MatchDto> recentMatches = summonerService.getRecentMatches(summoner.getPuuid());
        model.addAttribute("recentMatches", recentMatches);

        double winRate = summonerService.getWinRate(summoner.getSummonerId());
        model.addAttribute("winRate", winRate);

        return "summonerDetail";
    }

    @GetMapping("/matchDetails")
    public String getMatchDetails(String matchId, Model model) {
        MatchDto match = summonerService.getMatchDetails(matchId);
        model.addAttribute("match", match);
        return "matchDetail";
    }
}
