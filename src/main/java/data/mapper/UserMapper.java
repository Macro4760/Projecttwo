package data.mapper;

import data.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public void insertUser(UserDto dto); // 회원가입
    public UserDto getUserByEmail(String email); // 이메일로 회원 조회
    public UserDto getUserById(int id);
    public int checkEmail(String email); // 이메일 중복 체크
    public int loginCheck(String email, String password); // 로그인 체크
    public void updateUser(UserDto user);
    public void deleteUser(int id);
    public void changePhoto(String photo,int id);
}