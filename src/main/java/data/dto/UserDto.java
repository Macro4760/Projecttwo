package data.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String email;
    private String password;
    private String nickname;
    private String role;
    private String photo;
    private String createdAt;
    private MultipartFile upload;
}