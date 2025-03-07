package data.service;

import data.dto.UserDto;
import data.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 회원가입
    public void insertUser(UserDto dto) {
        userMapper.insertUser(dto);
    }

    // 이메일 중복 체크
    public boolean isEmailCheck(String email) {
        return userMapper.checkEmail(email) == 1;
    }

    // 로그인 체크
    public boolean loginCheck(String email, String password) {
        return userMapper.loginCheck(email, password) == 1;
    }

    // 이메일로 회원 정보 조회
    public UserDto getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }
    
    public UserDto getUserById(int id)
    {
    	return userMapper.getUserById(id);
    }
    // 회원 수정
    public void updateUser(UserDto user) {
        userMapper.updateUser(user);
    }

    // 회원 삭제
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
    
    public void changePhoto(String photo,int id)
    {
    	userMapper.changePhoto(photo, id);
    }
}