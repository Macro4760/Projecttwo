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
	        Map<String, Object> params = new HashMap<>();
	        params.put("championId", championId);

	        // 평균 평점과 참여자 수를 데이터베이스에서 가져옴
	        double avgRating = ratingMapper.getAverageRating(params);
	        int ratingCount = ratingMapper.getRatingCount(params);

	        // 결과를 Map에 담아서 반환
	        Map<String, Object> result = new HashMap<>();
	        result.put("avgRating", avgRating);
	        result.put("ratingCount", ratingCount);

	        return result;
	    }
	
	 
}
