package data.dto;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
 
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Alias("CommentDto")
public class CommentDto {
	private int id;
	private String championId;
	private String userId;
	private String comment;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createdAt;
}
