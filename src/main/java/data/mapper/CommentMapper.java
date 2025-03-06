package data.mapper;

import data.dto.ChampionDto;
import data.dto.CommentDto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
	
	public void insertComment(CommentDto commentDto);
    public List<CommentDto> selectCommentByChampionId(String championId);

    
}