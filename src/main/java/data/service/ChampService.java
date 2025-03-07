package data.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.dto.ChampionDto;
import data.dto.CommentDto;
import data.dto.RatingDto;
import data.mapper.ChampMapper;
import data.mapper.CommentMapper;
import data.mapper.RatingMapper;
import lombok.extern.java.Log;


@Service
public class ChampService {
	private final String DDRAGON_BASE_URL = "https://ddragon.leagueoflegends.com/cdn/14.4.1/data/ko_KR/";
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;


	@Autowired
	private CommentMapper commentMapper; // 필드 주입 방식
	@Autowired
	private ChampMapper champMapper; // 필드 주입 방식
	private final RatingMapper ratingMapper;
	private final CommentService commentService;


	private static final Logger log = LoggerFactory.getLogger(ChampService.class);


	@Autowired
	private SqlSession sqlSession;



	public ChampService(RestTemplate restTemplate, RatingMapper ratingMapper, CommentService commentService) {
        this.restTemplate = restTemplate;
        this.ratingMapper = ratingMapper;
        this.commentService = commentService;
        this.objectMapper = new ObjectMapper();
    }



	public List<ChampionDto> fetchChampions() {
		// JSON 파일 경로
		String jsonFilePath = "classpath:championFull.json"; // championfull.json을 사용

		// JSON 데이터를 읽어오는 로직
		String json = "";
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("championFull.json")) {
			if (inputStream != null) {
				json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
			} else {
				System.out.println("Error: championFSull.json file not found.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// champions 리스트 생성
		List<ChampionDto> champions = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			JsonNode root = objectMapper.readTree(json).get("data");
			Iterator<String> fieldNames = root.fieldNames();

			while (fieldNames.hasNext()) {
				String champId = fieldNames.next();
				JsonNode champData = root.get(champId);

				// ChampionDto 생성
				ChampionDto champion = ChampionDto.builder()
						.id(champData.get("id").asText())
						.name(champData.get("name").asText())
						.title(champData.get("title").asText())
						.blurb(champData.get("blurb").asText())
						.image("https://ddragon.leagueoflegends.com/cdn/14.4.1/img/champion/" + champData.get("id").asText() + ".png")
						.skillQ(champData.get("id").asText() + "Q")
						.skillQName(champData.get("spells").get(0).get("name").asText())
						.skillQDescription(champData.get("spells").get(0).get("description").asText())
						.skillQImage(champData.get("spells").get(0).get("id").asText() + ".png")
						.skillW(champData.get("id").asText() + "W")
						.skillWName(champData.get("spells").get(1).get("name").asText())
						.skillWDescription(champData.get("spells").get(1).get("description").asText())
						.skillWImage(champData.get("spells").get(1).get("id").asText() + ".png")
						.skillE(champData.get("id").asText() + "E")
						.skillEName(champData.get("spells").get(2).get("name").asText())
						.skillEDescription(champData.get("spells").get(2).get("description").asText())
						.skillEImage(champData.get("spells").get(2).get("id").asText() + ".png")
						.skillR(champData.get("id").asText() + "R")
						.skillRName(champData.get("spells").get(3).get("name").asText())
						.skillRDescription(champData.get("spells").get(3).get("description").asText())
						.skillRImage(champData.get("spells").get(3).get("id").asText() + ".png")
						.build();

				// champions 리스트에 추가
				champions.add(champion);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return champions;
	}

	public ChampionDto getChampionById(String championId) {
		List<ChampionDto> champions = fetchChampions();
		if (champions != null && !champions.isEmpty()) {
			// 첫 번째 챔피언을 가져와서 스킬 정보를 확인하는 로그 추가
			ChampionDto champion = champions.get(0); // 또는 원하는 조건으로 champion을 선택
			log.info("Skill Q Description: {}", champion.getSkillQDescription());
			log.info("Skill Q Name: {}", champion.getSkillQName());
		}
		return champions.stream()
				.filter(champion -> champion.getId().equals(championId))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Champion not found"));
	}

	public ChampionDto getChampionWithRatingAndComments(String championId) {
		ChampionDto champion = champMapper.findChampionById(championId);
		if (champion != null) {
			champion.setRating(champMapper.getChampionRating(championId));  // 평점 조회
			champion.setComments(champMapper.getChampionComments(championId));  // 댓글 조회
		}
		return champion;
	}

	@GetMapping("/detail/{championId}")
	public String getChampionDetail(@PathVariable String championId, Model model) {
		// 챔피언 정보 조회
		ChampionDto champion = champMapper.findChampionById(championId);

		// 해당 챔피언에 대한 댓글 목록 조회
		List<CommentDto> comments = commentService.getCommentsByChampionId(championId);

		// 모델에 데이터를 추가하여 뷰로 전달
		model.addAttribute("champion", champion);
		model.addAttribute("comments", comments);

		return "champion/detail";  // champion/detail.jsp로 이동
	}

	// 평점 저장
	public void saveRating(Map<String, Object> ratingData) {
		// championId 값을 Map에서 꺼내기
		String championId = (String) ratingData.get("championId");

		// champion 테이블에 championId가 존재하는지 확인
		int count = champMapper.checkChampionExist(championId);
		if (count == 0) {
			throw new IllegalArgumentException("Invalid champion ID: " + championId);
		}

		// champion_id가 존재하면, champion_ratings 테이블에 데이터 삽입
		champMapper.insertChampionRating(ratingData);
	}

	// 댓글 저장
	public void saveComment(CommentDto commentDto) {
		Map<String, Object> params = new HashMap<>();
		params.put("championId", commentDto.getChampionId());
		params.put("userId", commentDto.getUserId());
		params.put("content", commentDto.getComment());
		champMapper.insertComment(params);  // 댓글 DB에 저장
	}
	public void insertChampionRating(int championId, double rating) {
		Map<String, Object> params = new HashMap<>();
		params.put("championId", championId);
		params.put("rating", rating);
		champMapper.insertChampionRating(params);
	}

	public void saveChampionsFromJson() {
		try {
			// ✅ JSON 파일 읽기 (파일 경로를 직접 입력)
			File file = new File("src/main/resources/static/champion.json");
			JsonNode root = objectMapper.readTree(file);  // readTree는 객체로 호출

			// ✅ 챔피언 데이터 가져오기
			JsonNode championData = root.get("data");

			// ✅ 모든 챔피언 저장
			Iterator<String> keys = championData.fieldNames();
			while (keys.hasNext()) {
				String key = keys.next();
				JsonNode champ = championData.get(key);

				ChampionDto dto = new ChampionDto();
				dto.setId(champ.get("id").asText());
				dto.setName(champ.get("name").asText());
				dto.setTitle(champ.get("title").asText());
				dto.setBlurb(champ.get("blurb").asText());
				dto.setImage(champ.get("image").get("full").asText());

				// ✅ DB에 저장
				champMapper.insertChampion(dto);
				System.out.println("✅ 챔피언 저장 완료: " + dto.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getChampionRatingStats(String championId) {
		return champMapper.getChampionRatingStats(championId);
	}




}



