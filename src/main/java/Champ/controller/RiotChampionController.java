package Champ.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.dto.ChampionDto;
import data.dto.CommentDto;
import data.dto.RatingDto;
import data.mapper.ChampMapper;
import data.mapper.CommentMapper;
import data.service.ChampService;
import data.service.CommentService;
import data.service.RatingService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/champion")
public class RiotChampionController {
	private final ChampService riotChampionService;
    private final CommentService commentService;
    private static final String Champion_File="src/main/resources/static/championFull.json";
    private final RatingService ratingService;
    private final CommentMapper commentMapper;
    
	@Autowired
    public RiotChampionController(ChampService riotChampionService, CommentService commentService, RatingService ratingService,CommentMapper commentMapper) {
        this.riotChampionService = riotChampionService;
        this.commentService = commentService;
        this.ratingService = ratingService; // Spring이 RatingService를 자동으로 주입한다.\
        this.commentMapper = commentMapper;
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
	    public String getChampionDetail(@PathVariable String championId, Model model) throws IOException {
	        // JSON 파일에서 챔피언 정보 가져오기
		// 평균 평점과 평점 개수 가져오기
		 	Double avgRating = ratingService.getAverageRating(championId);
		 	Integer ratingCount = ratingService.getRatingCount(championId);

	        // null 처리, 값이 없으면 기본값(0.0, 0명 참여)으로 설정
	        model.addAttribute("avgRating", (avgRating != null ? avgRating : 0.0));
	        model.addAttribute("ratingCount", (ratingCount != null ? ratingCount : 0));
	        ObjectMapper objectMapper = new ObjectMapper();
	        File file = new File(Champion_File);
	        Map<String, Map<String, Object>> json = objectMapper.readValue(file, Map.class);
	        
	        // 챔피언 데이터 가져오기
	        Map<String, Object> championData = (Map<String, Object>) json.get("data").get(championId);
	        ChampionDto champion = new ChampionDto();
	        champion.setId((String) championData.get("id"));
	        champion.setName((String) championData.get("name"));
	        champion.setTitle((String) championData.get("title"));
	        champion.setBlurb((String) championData.get("blurb"));
	        
	        Map<String, Object> image = (Map<String, Object>) championData.get("image");
	        if (image != null) {
	            champion.setImage((String) image.get("full"));
	        }
	        
	        // 스킬 정보 가져오기
	        List<Map<String, Object>> skills = (List<Map<String, Object>>) championData.get("spells");
	        if (skills != null && skills.size() > 0) {
	            champion.setSkillQ((String) skills.get(0).get("id"));
	            champion.setSkillQName((String) skills.get(0).get("name"));
	            champion.setSkillQDescription((String) skills.get(0).get("description"));
	            champion.setSkillQImage((String) ((Map<String, Object>) skills.get(0).get("image")).get("full"));
	            
	            champion.setSkillW((String) skills.get(1).get("id"));
	            champion.setSkillWName((String) skills.get(1).get("name"));
	            champion.setSkillWDescription((String) skills.get(1).get("description"));
	            champion.setSkillWImage((String) ((Map<String, Object>) skills.get(1).get("image")).get("full"));
	            
	            champion.setSkillE((String) skills.get(2).get("id"));
	            champion.setSkillEName((String) skills.get(2).get("name"));
	            champion.setSkillEDescription((String) skills.get(2).get("description"));
	            champion.setSkillEImage((String) ((Map<String, Object>) skills.get(2).get("image")).get("full"));
	            
	            champion.setSkillR((String) skills.get(3).get("id"));
	            champion.setSkillRName((String) skills.get(3).get("name"));
	            champion.setSkillRDescription((String) skills.get(3).get("description"));
	            champion.setSkillRImage((String) ((Map<String, Object>) skills.get(3).get("image")).get("full"));
	        }
	        
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

	
	
	
	 @PostMapping("/rating")
	 public ResponseEntity<Map<String, Object>> saveRating(@RequestParam String championId, @RequestParam int rating) {
	     try {
	         RatingDto ratingDto = new RatingDto(championId, rating);
	         ratingService.insertRating(ratingDto);  // 평점 저장
	         Map<String, Object> ratingStats = ratingService.getRatingStats(championId);  // 통계 조회
	         return ResponseEntity.ok(ratingStats);  // 통계값 반환
	     } catch (IllegalArgumentException e) {
	         // 챔피언이 존재하지 않을 경우 예외 처리
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
	     } catch (Exception e) {
	         // 기타 예외 처리
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error saving rating"));
	     }
	 }


	 	@PostMapping("/submit")
	    @ResponseBody
	    public Map<String, Object> submitRating(@RequestBody RatingDto ratingDto) {
	        // 평점 정보 DB에 저장
	        ratingService.insertRating(ratingDto);
	        
	        // 업데이트된 평점 통계 반환
	        return ratingService.getRatingStats(ratingDto.getChampionId());
	    }

    
	 	@PostMapping("/comment")
	 	@ResponseBody
	 	public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto) {
	 	    System.out.println("💡 Received CommentDTO: " + commentDto);

	 	    // null 체크
	 	    if (commentDto.getChampionId() == null) {
	 	        System.out.println("❌ 챔피언 ID가 null입니다! 프론트에서 데이터가 안 넘어온 것 같습니다.");
	 	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("챔피언 ID가 없습니다.");
	 	    }

	 	    commentMapper.insertComment(commentDto);
	 	    return ResponseEntity.ok("댓글이 등록되었습니다.");
	 	}
	 	
	 	@GetMapping("/comments")
	 	@ResponseBody
	 	public ResponseEntity<Map<String, Object>> getComments(@RequestParam String championId) {
	 	    try {
	 	        System.out.println("🔍 댓글 조회 요청 - 챔피언 ID: " + championId);

	 	        List<CommentDto> comments = commentMapper.selectCommentByChampionId(championId);
	 	        System.out.println("✅ 불러온 댓글 개수: " + comments.size());

	 	        Map<String, Object> response = new HashMap<>();
	 	        response.put("comments", comments);
	 	        return ResponseEntity.ok(response);
	 	    } catch (Exception e) {
	 	        System.out.println("❌ 댓글 조회 중 오류 발생!");
	 	        e.printStackTrace();  // 예외 로그 출력
	 	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 	                .body(Map.of("message", "댓글을 불러오지 못했습니다."));
	 	    }
	 	}
	 	
	 	@DeleteMapping("/deleteComment")
	 	@ResponseBody
	 	public Map<String, Object> deleteComment(@RequestParam("id") int id) {
	 	    System.out.println("삭제할 댓글 ID: " + id); // 삭제할 ID 로그 확인
	 	    if (id <= 0) {
	 	        return Collections.singletonMap("success", false);  // 잘못된 ID 처리
	 	    }
	 	    int result = commentService.deleteComment(id);  // 삭제된 행의 개수
	 	    Map<String, Object> response = new HashMap<>();
	 	    response.put("success", result > 0);  // 삭제된 행이 1개 이상이면 성공
	 	    return response;
	 	}


	 	




	 	

    
}

