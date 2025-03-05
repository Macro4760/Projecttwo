package ChampController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import data.dto.ChampionDto;
import data.service.ChampService;

@Controller
@RequestMapping("/riot/champion")
public class RiotChampionController {
    private final ChampService riotChampionService;

    public RiotChampionController(ChampService riotChampionService) {
        this.riotChampionService = riotChampionService;
    }
    
    @GetMapping("/listPage")
    public String listPage(Model model) {
    	List<ChampionDto> champions = riotChampionService.getStoredChampions();
    	 System.out.println("Champions: " + champions);  
    	model.addAttribute("champions",champions);
    	return "championList";
    }

    // 챔피언 데이터 가져와서 DB 저장
    @PostMapping("/fetch")
    public ResponseEntity<List<ChampionDto>> fetchChampions() {
        List<ChampionDto> champions = riotChampionService.fetchChampions();
        return ResponseEntity.ok(champions);
    }

    // DB에 저장된 챔피언 목록 반환
    @GetMapping("/list")
    public ResponseEntity<List<ChampionDto>> getChampions() {
        return ResponseEntity.ok(riotChampionService.getStoredChampions());
    }

    // 개별 챔피언 정보 가져오기
    @GetMapping("/detail")
    public ResponseEntity<String> getChampionDetail(@RequestParam String name) {
        return ResponseEntity.ok(riotChampionService.getChampionDetail(name));
    }
}

