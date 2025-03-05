package data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import data.dto.UserDto;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(UserDto user);
    UserDto getUserByEmail(@RequestParam("email") String email);
    UserDto getUserByNickname(@RequestParam("nickname") String nickname);
    void updateUser(UserDto user);
    void deleteUser(@RequestParam("id") int id);
    List<UserDto> getAllUsers();
}