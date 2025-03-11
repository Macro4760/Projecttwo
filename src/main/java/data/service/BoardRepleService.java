package data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import data.dto.BoardRepleDto;
import data.mapper.BoardRepleMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardRepleService {
	

	BoardRepleMapper boardRepleMapper;
	
	public List<BoardRepleDto> getSelectReples(int idx)
	{
		return boardRepleMapper.getSelectReples(idx);
	}
	public void insertReple(BoardRepleDto rdto)
	{
		boardRepleMapper.insertReple(rdto);
	}
	public void updateReple(BoardRepleDto rdto)
	{
		boardRepleMapper.updateReple(rdto);
	}
	public void deleteReple(int idx)
	{
		boardRepleMapper.deleteReple(idx);
	}
}
