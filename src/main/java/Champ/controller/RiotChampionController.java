package Champ.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.dto.ChampionDto;
import data.dto.CommentDto;
import data.mapper.CommentMapper;
import data.service.ChampService;
import data.service.CommentService;

@Controller
@RequestMapping("/champion")
public class RiotChampionController {
	private final ChampService riotChampionService;
	private final CommentService commentService;
	private static final String Champion_File="src/main/resources/static/championFull.json";
	

	public RiotChampionController(ChampService riotChampionService,CommentService commentService) {
		this.riotChampionService = riotChampionService;
		this.commentService = commentService;
	}

	@GetMapping("/list")
	public String getChampions(Model model) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		// JSON 파일 읽기
		File file = new File(Champion_File);
		Map<String, Map<String, Object>> json = objectMapper.readValue(file, Map.class);

		List<ChampionDto> championList = new ArrayList<>();
		Map<String, Object> data = json.get("data");
		for (String key : data.keySet()) {
			Map<String, Object> championData = (Map<String, Object>) data.get(key);
			ChampionDto champion = new ChampionDto();
			champion.setId((String) championData.get("id"));
			champion.setName((String) championData.get("name"));
			champion.setTitle((String) championData.get("title"));
			champion.setBlurb((String) championData.get("blurb"));
			champion.setImage((String) ((Map<String, Object>) championData.get("image")).get("full"));
			championList.add(champion);
		}

		// 모델에 담아서 JSP로 전달
		model.addAttribute("championList", championList);
		return "championList";  // JSP 파일 이름
	}

	@GetMapping("/championList")
	public String getChampionList(Model model) {
		// 챔피언 리스트를 가져와서 모델에 담기
		List<ChampionDto> championList = riotChampionService.fetchChampions();
		System.out.println("Champion List: " + championList); 
		model.addAttribute("championList", championList);
		return "championList";  // JSP 파일 이름 (championList.jsp)
	}

	@GetMapping("/detail/{championId}")
	public String getChampionDetail(@PathVariable("championId") String championId, Model model) {
		System.out.println("Received Champion ID: " + championId); // 이 라인이 출력되야 함
		ChampionDto champion = riotChampionService.getChampionById(championId);
		model.addAttribute("champion", champion);
		return "championDetail";
	}

	/*
	 * @PostMapping("/rating") public ResponseEntity<String>
	 * saveChampionRating(@RequestParam("championId") String championId,
	 * 
	 * @RequestParam("rating") int rating) {
	 * riotChampionService.saveRating(championId, rating); // Service 호출 return
	 * ResponseEntity.ok("Rating saved successfully"); // 성공 메시지 }
	 */

	@PostMapping("/comment")
	public ResponseEntity<String> saveChampionComment(@RequestParam("championId") String championId, 
			@RequestParam("comment") String comment) {
		riotChampionService.saveComment(championId, comment);
		return ResponseEntity.ok("Comment saved successfully");
	}
	@PostMapping("/addComment")
	public String addComment(@ModelAttribute CommentDto commentDto) {
	    commentService.addComment(commentDto);  // Service를 통해 DB에 댓글 저장
	    return "redirect:/championDetail?championId=" + commentDto.getChampionId();  // 댓글이 추가된 후 다시 챔피언 상세 페이지로 리다이렉트
	}
	
	@PostMapping("/saveRating")
	public String saveRating(@RequestParam String championId, @RequestParam int rating) {
	    // ratingData Map 생성
	    Map<String, Object> ratingData = new HashMap<>();
	    ratingData.put("championId", championId);
	    ratingData.put("rating", rating);

	    // saveRating 메소드 호출
	    riotChampionService.saveRating(ratingData);

	    return "redirect:/detail/" + championId;
	}



}

