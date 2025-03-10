package data.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.dto.ChampionDto;
import data.dto.CommentDto;
import data.mapper.ChampMapper;
import data.mapper.CommentMapper;


@Service
public class CommentService {

    private CommentMapper commentMapper;
	
	@Autowired
	public CommentService(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	public void insertComment(CommentDto commentDto) {
        commentMapper.insertComment(commentDto);  // DB에 댓글 추가
    }
	

    // 챔피언에 해당하는 댓글 목록을 가져오는 메서드
    public List<CommentDto> getCommentsByChampionId(String championId) {
        return commentMapper.selectCommentByChampionId(championId);
    }
    
    public int deleteComment(int id) {
        return commentMapper.deleteComment(id);
    }
   
}



