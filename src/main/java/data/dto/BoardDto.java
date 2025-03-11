package data.dto;

import java.util.List;
import java.sql.Timestamp;


import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("BoardDto")
public class BoardDto {
    
    private int idx;                // 게시글 고유 ID
    private String email;            // 작성자 이메일
    private String title;            // 게시글 제목
    private String content;          // 게시글 내용
    private String writer;           // 작성자
    private String nickname;         // 작성자 닉네임
    private int readcount;           // 조회수
    private Timestamp createdate;          // 작성일자 (java.util.Date로 수정)
    private boolean isNotice;        // 공지글 여부 (boolean으로 수정)

    private List<String> photos;     // 사진 리스트
    private int repleCount;          // 댓글 수
}
