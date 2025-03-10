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

	public void insertComment(CommentDto commentDto);  // 댓글을 추가하는 메서드
	public List<CommentDto> selectCommentByChampionId(String championId);
	
    public ChampionDto getChampionById(String championId);
    
    public int deleteComment(int id);


}