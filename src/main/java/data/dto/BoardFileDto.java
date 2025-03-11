package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("BoardFileDto")
public class BoardFileDto {
	private int num;
	private int idx;
	private String filename;	
}