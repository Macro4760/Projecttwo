package data.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.dto.ChampionDto;
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

    // 전체 챔피언 데이터 가져오기
    public List<ChampionDto> fetchChampions() {
        String url = DDRAGON_BASE_URL + "champion.json";
        String json = restTemplate.getForObject(url, String.class);

        List<ChampionDto> champions = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode root = objectMapper.readTree(json).get("data");
            Iterator<String> fieldNames = root.fieldNames();

            while (fieldNames.hasNext()) {
                String champId = fieldNames.next();
                JsonNode champData = root.get(champId);

                ChampionDto champion = new ChampionDto(
                        champData.get("id").asText(),
                        champData.get("name").asText(),
                        champData.get("title").asText(),
                        champData.get("blurb").asText(),
                        "https://ddragon.leagueoflegends.com/cdn/14.4.1/img/champion/" + champId + ".png"
                );

                champions.add(champion);
                champMapper.insertChampion(champion);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return champions;
    }

    // 특정 챔피언 상세 정보 가져오기
    public String getChampionDetail(String championName) {
        String url = DDRAGON_BASE_URL + "champion/" + championName + ".json";
        return restTemplate.getForObject(url, String.class);
    }

    // DB에서 챔피언 목록 가져오기
    public List<ChampionDto> getStoredChampions() {
        return champMapper.findAllChampions();
    }
}
