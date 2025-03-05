package data.mapper;

import data.dto.ChampionDto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChampMapper {

    @Insert("INSERT INTO champion (id, name, title, blurb, image) VALUES (#{id}, #{name}, #{title}, #{blurb}, #{image})")
    void insertChampion(ChampionDto champion);

    @Select("SELECT * FROM champion WHERE id = #{id}")
    ChampionDto findChampionById(String id);

    @Select("SELECT * FROM champion")
    List<ChampionDto> findAllChampions();
}