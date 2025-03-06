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
	@Autowired
    private CommentMapper commentMapper;

    public void addComment(CommentDto commentDto) {
        commentMapper.insertComment(commentDto);  // MyBatis 매퍼 메서드를 호출해 DB에 댓글 추가
    }
}



