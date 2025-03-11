package data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.BoardDto;
import data.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	BoardMapper boardMapper;
	public int getTotalCount()
	{
		return boardMapper.getTotalCount();
	}
	public List<BoardDto> getPagingList(int start,int perpage)
	{
		return boardMapper.getPagingList(start, perpage);
	}
	public BoardDto getNotice() {
        return boardMapper.getNotice();
    }
	public List<BoardDto> getBoardList()
	{
		return boardMapper.getBoardList();
	}
	public BoardDto getSelectByIdx(int idx) 
	{
		return boardMapper.getSelectByIdx(idx);
	}
	public List<BoardDto> getSelectByEmail(String email)
	{
		return boardMapper.getSelectByEmail(email);
	}
	public void updateReadCount(int idx) 
	{
		boardMapper.updateReadcount(idx);
	}
	public void insertBoard(BoardDto dto)
	{
		boardMapper.insertBoard(dto);
	}
	public void updateBoard(BoardDto dto)
	{
		boardMapper.updateBoard(dto);
	}
	public void deleteBoard(int idx)
	{
		boardMapper.deleteBoard(idx);
	}
}