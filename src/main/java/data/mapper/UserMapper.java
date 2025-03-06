package data.mapper;

import data.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(UserDto dto); // 회원가입
    UserDto getUserByEmail(String email); // 이메일로 회원 조회
    int checkEmail(String email); // 이메일 중복 체크
    int loginCheck(String email, String password); // 로그인 체크
}