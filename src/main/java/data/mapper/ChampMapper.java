package data.mapper;

import data.dto.ChampionDto;
import data.dto.CommentDto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChampMapper {

    public void insertChampion(ChampionDto champion);
    public ChampionDto findChampionById(String id);
    public List<ChampionDto> findAllChampions();
    public int getChampionRating(String championId);
    public List<CommentDto> getChampionComments(String championId);  // 수정
    public void insertComment(Map<String, Object> commentData);
    public int checkChampionExist(String championId);
    public void insertChampionRating(Map<String, Object> ratingData);
    public Map<String, Object> getChampionRatingStats(@Param("championId") String championId);
    public Map<String, Object> getRatingStats(String championId);
}