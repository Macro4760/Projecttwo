package data.mapper;

import data.dto.ChampionDto;
import data.dto.RatingDto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface RatingMapper {

    
    public void insertRating(RatingDto ratingDto);
    Double getAverageRating(@Param("championId") String championId);  // 평균 평점 가져오기
    Integer getRatingCount(@Param("championId") String championId);  // 평점 개수 가져오기
    public int saveRating(RatingDto rating);
    

}