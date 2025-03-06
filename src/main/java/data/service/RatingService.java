package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.RatingDto;
import data.mapper.ChampMapper;
import data.mapper.RatingMapper;    // 이 부분 확인


@Service
public class RatingService {

	 @Autowired
	    private ChampMapper championMapper;

	    @Autowired
	    private RatingMapper ratingMapper;

	public void insertRating(RatingDto ratingDto) {
		// champion_id가 실제로 존재하는지 확인
		int count = championMapper.checkChampionExist(ratingDto.getChampionId());
		if (count == 0) {
			throw new IllegalArgumentException("해당 챔피언이 존재하지 않습니다.");
		}

		// champion_id가 존재한다면, rating 삽입
		ratingMapper.insertRating(ratingDto);
	}
}
