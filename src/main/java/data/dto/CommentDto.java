package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Alias("commentDto")
public class CommentDto {
	 	private String championId;
	    private String userId;
	    private String content;
}
