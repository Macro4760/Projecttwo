package data.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.RatingDto;
import data.mapper.ChampMapper;
import data.mapper.RatingMapper;    // 이 부분 확인


@Service
public class RatingService {

	 	
	    private ChampMapper champMapper;
	    private RatingMapper ratingMapper;
	 	
	    @Autowired
	    public RatingService(ChampMapper champMapper, RatingMapper ratingMapper) {
	        this.champMapper = champMapper;
	        this.ratingMapper = ratingMapper;
	    }
	    
	    
	    
	    public Double getAverageRating(String championId) {
	        return ratingMapper.getAverageRating(championId);
	    }

	    // 평점 개수 가져오기
	    public Integer getRatingCount(String championId) {
	        return ratingMapper.getRatingCount(championId);
	    }



	    public void insertRating(RatingDto ratingDto) {
	        // champion_id가 실제로 존재하는지 확인
	        int count = champMapper.checkChampionExist(ratingDto.getChampionId());
	        if (count == 0) {
	            throw new IllegalArgumentException("해당 챔피언이 존재하지 않습니다.");
	        }

	        // champion_id가 존재한다면, rating 삽입
	        ratingMapper.insertRating(ratingDto);
	    }
	
	
	    public Map<String, Object> getRatingStats(String championId) {
	        // championId는 String으로 넘어오므로 변환할 필요 없음
	        // championId가 이미 String으로 되어 있으므로 그대로 사용

	        // 평균 평점과 참여자 수를 데이터베이스에서 가져옴
	        Double avgRating = ratingMapper.getAverageRating(championId);  // championId는 String 타입
	        Integer ratingCount = ratingMapper.getRatingCount(championId);  // championId는 String 타입

	        // 결과를 Map에 담아서 반환
	        Map<String, Object> result = new HashMap<>();
	        result.put("avgRating", avgRating);
	        result.put("ratingCount", ratingCount);

	        return result;
	    }


	
	 
}
