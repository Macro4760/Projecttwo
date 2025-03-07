package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Alias("RatingDto")
@NoArgsConstructor
public class RatingDto {
	private String championId;
	private int rating;
	
	public RatingDto(String championId, int rating) {
        this.championId = championId;
        this.rating = rating;
    }
}
