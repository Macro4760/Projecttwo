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

    
    void insertRating(RatingDto ratingDto);
}