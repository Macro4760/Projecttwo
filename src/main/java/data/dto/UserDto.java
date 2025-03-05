package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("UserDto")
public class UserDto {
	private int id;
    private String email;
    private String password;
    private String nickname;
    private String role;
    private String photo;
    private String createdAt;
}
