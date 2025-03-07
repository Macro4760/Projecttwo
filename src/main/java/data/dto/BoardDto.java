package data.dto;

import java.security.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Data
@Alias("BoardDto")
public class BoardDto {
	
	    private int idx;            // 게시글 고유 ID
	    private String title;       // 게시글 제목
	    private String content;     // 게시글 내용
	    private String nickname;    // 작성자 (nickname)
	    private Timestamp createdAt; // 작성일자
	    private Timestamp updatedAt; // 수정일자
	    private int isNotice;       // 공지글 여부

}

