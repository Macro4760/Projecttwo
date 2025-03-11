package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Alias("CommentDto")
public class CommentDto {
	private String championId;
	private String userId;
	private String comment;
	private String createdate;  // 댓글 생성 시간
}
