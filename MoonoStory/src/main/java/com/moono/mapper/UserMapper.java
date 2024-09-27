package com.moono.mapper;

import com.moono.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    
    @Insert("INSERT INTO user (id, pwd, nickName) VALUES (#{id}, #{pwd}, #{nickName})")
    void registerUser(UserDTO user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserDTO findUserById(String id);
    
 // 미션 상태 업데이트 - UPDATE
    @Update("UPDATE user SET oneMission = #{oneMission}, twoMission = #{twoMission}, threeMission = #{threeMission}, " +
            "fourMission = #{fourMission}, fiveMission = #{fiveMission} WHERE id = #{id}")
    void updateMissionStatus(UserDTO user);
    
}