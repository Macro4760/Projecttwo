
package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.BoardFileDto;

@Mapper
public interface BoardFileMapper {
	public void insertBoardFile(BoardFileDto dto);
	public List<BoardFileDto> getFiles(int idx);
	public void deleteFile(int num);
	public String getFilename(int num);
}
