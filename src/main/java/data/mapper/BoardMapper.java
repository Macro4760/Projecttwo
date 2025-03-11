package data.mapper;

import data.dto.BoardDto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface BoardMapper {
	 public int getTotalCount();
	 public List<BoardDto> getPagingList(int start,int perpage);
	 public List<BoardDto> getBoardList();
	 public BoardDto getNotice();
	 public void updateReadcount(int idx);
	 public void insertBoard(BoardDto dto);
	 public void updateBoard(BoardDto dto);
	 public void deleteBoard(int idx);
	 public List<BoardDto> getSelectByEmail(String email);
	 public BoardDto getSelectByIdx(int idx);
}
