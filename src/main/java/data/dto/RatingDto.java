package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Alias("ratingDto")
public class RatingDto {
	private String championId;
	private int rating;
}
