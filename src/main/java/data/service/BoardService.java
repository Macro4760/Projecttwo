package data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.BoardDto;
import data.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	public List<BoardDto> selectNoticeBoards()
	{
		return boardMapper.selectNoticeBoards();
	}
	
	public List<BoardDto> selectAllBoards()
	{
		return boardMapper.selectAllBoards();
	}
	void insertBoard(BoardDto board)
	{
		boardMapper.insertBoard(board);
	}
}
