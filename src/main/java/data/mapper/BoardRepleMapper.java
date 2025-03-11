package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.BoardRepleDto;

@Mapper
public interface BoardRepleMapper {
	 public List<BoardRepleDto> getSelectReples(int idx);
	 public void insertReple(BoardRepleDto rdto);
	 public void updateReple(BoardRepleDto rdto);
	 public void deleteReple(int idx);
}
