package data.dto;


import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("BoardRepleDto")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRepleDto {
	
	    private int num;           // 댓글 고유 ID
	    private int idx;           // 게시글 ID
	    private String email;
	    private String writer;
	    private String profile;
	    private String message;         // 댓글 내용
	    private String nickname;        // 댓글 작성자
	    private java.sql.Timestamp createdate;       // 댓글 작성 일자

	   
	}
