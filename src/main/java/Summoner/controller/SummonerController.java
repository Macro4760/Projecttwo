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

    @GetMapping
    public String getSummonerInfo(@RequestParam String summonerName, Model model) {
        // 소환사 정보 가져오기
        SummonerDto summoner = summonerService.getSummonerInfo(summonerName);
        if (summoner == null) {
            // 소환사 정보를 찾을 수 없을 경우 errorPage로 이동
            model.addAttribute("error", "소환사를 찾을 수 없습니다.");
            return "errorPage";
        }
        // 소환사 정보 모델에 추가
        model.addAttribute("summoner", summoner);

        // 최근 매치 정보 가져오기
        List<MatchDto> recentMatches = summonerService.getRecentMatches(summoner.getPuuid());
        model.addAttribute("recentMatches", recentMatches);

        // 승률 계산하여 모델에 추가
        double winRate = summonerService.getWinRate(summoner.getSummonerId());
        model.addAttribute("winRate", winRate);

        // 소환사 상세 정보를 담은 페이지로 이동
        return "summonerDetail";
    }


    @GetMapping("/matchDetails")
    public String getMatchDetails(String matchId, Model model) {
        MatchDto match = summonerService.getMatchDetails(matchId);
        model.addAttribute("match", match);
        return "matchDetail";
    }
}
