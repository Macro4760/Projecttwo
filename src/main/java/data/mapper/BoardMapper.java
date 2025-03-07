package data.mapper;

import data.dto.BoardDto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface BoardMapper {
    // 공지글 조회
    List<BoardDto> selectNoticeBoards();

    // 일반 게시글 조회 (공지글 제외)
    List<BoardDto> selectAllBoards();

    // 게시글 삽입
    void insertBoard(BoardDto board);
}
