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

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.dto.ChampionDto;
import data.dto.RatingDto;
import data.mapper.ChampMapper;


@Service
public class ChampService {
	private final String DDRAGON_BASE_URL = "https://ddragon.leagueoflegends.com/cdn/14.4.1/data/ko_KR/";
	private final RestTemplate restTemplate;
	private final ChampMapper champMapper;
	

	public ChampService(RestTemplate restTemplate, ChampMapper championMapper) {
		this.restTemplate = restTemplate;
		this.champMapper = championMapper;
	}

	public List<ChampionDto> fetchChampions() {
		// JSON 파일 경로
		String jsonFilePath = "classpath:championfull.json"; // championfull.json을 사용

		// JSON 데이터를 읽어오는 로직
		String json = "";
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("championfull.json")) {
			if (inputStream != null) {
				json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
			} else {
				System.out.println("Error: championfull.json file not found.");
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
	public void saveComment(String championId, String comment) {
		Map<String, Object> params = new HashMap<>();
		params.put("championId", championId);
		params.put("comment", comment);
		champMapper.insertComment(params);
	}
	public void insertChampionRating(int championId, double rating) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("championId", championId);
	    params.put("rating", rating);
	    champMapper.insertChampionRating(params);
	}
}



