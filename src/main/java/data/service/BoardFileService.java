package data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import data.dto.BoardFileDto;
import data.mapper.BoardFileMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardFileService {
	BoardFileMapper fileMapper;
	
	public void insertBoardFile(BoardFileDto dto)
	{
		fileMapper.insertBoardFile(dto);
	}
	
	public List<BoardFileDto> getFiles(int idx)
	{
		return fileMapper.getFiles(idx);
	}
	public void deleteFile(int num)
	{
		fileMapper.deleteFile(num);
	}
	public String getFilename(int num)
	{
		return fileMapper.getFilename(num);
	}
}