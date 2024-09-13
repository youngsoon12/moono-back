package com.moono.mapper;

import com.moono.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    
    @Insert("INSERT INTO user (id, pwd, nickName) VALUES (#{id}, #{pwd}, #{nickName})")
    void registerUser(UserDTO user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserDTO findUserById(String id);
}