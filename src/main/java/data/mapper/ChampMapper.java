package data.mapper;

import data.dto.ChampionDto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChampMapper {

    public void insertChampion(ChampionDto champion);
    public ChampionDto findChampionById(String id);
    public List<ChampionDto> findAllChampions();
    public int getChampionRating(String championId);
    public List<String> getChampionComments(String championId);
    public void insertRating(Map<String, Object> ratingData);
    public void insertComment(Map<String, Object> commentData);
    
}