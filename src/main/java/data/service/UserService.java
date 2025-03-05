package data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.UserDto;
import data.mapper.UserMapper;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public void registerUser(UserDto userDto) {
        userMapper.insertUser(userDto);
    }

    public UserDto getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    public UserDto getUserByNickname(String nickname) {
        return userMapper.getUserByNickname(nickname);
    }

    public void updateUser(UserDto userDto) {
        userMapper.updateUser(userDto);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
    
    public List<UserDto> getAllUsers() {
        return userMapper.getAllUsers();
    }
}