package data.mapper;

import data.dto.ChampionDto;
import data.dto.RatingDto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface RatingMapper {

    
    public void insertRating(RatingDto ratingDto);
    public double getAverageRating(Map<String, Object> params);
    public int getRatingCount(Map<String, Object> params);
    public int saveRating(RatingDto rating);
}