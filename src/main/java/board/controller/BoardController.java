package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import data.dto.BoardDto;
import data.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시판 목록 페이지
    @GetMapping("/board/list")
    public String listBoards(Model model) {
        List<BoardDto> noticeBoards = boardService.selectNoticeBoards();
        List<BoardDto> allBoards = boardService.selectAllBoards();
        
        model.addAttribute("noticeBoards", noticeBoards);
        model.addAttribute("allBoards", allBoards);
        
        return "board/boardlist";
    }
}