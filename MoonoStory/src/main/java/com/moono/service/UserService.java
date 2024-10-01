package com.moono.service;

import com.moono.dto.UserDTO;
import com.moono.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void registerUser(UserDTO user) {
    	try {
    		userMapper.registerUser(user);
    	} catch (DuplicateKeyException e) {
            // 중복 키 예외 처리
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }
        
    }

    public UserDTO login(String id, String pwd) { 
        UserDTO user = userMapper.findUserById(id);
        if (user != null && user.getPwd().equals(pwd)) {
            return user; // 로그인 성공
        }
        return null; // 로그인 실패
    }
    
    public UserDTO findUserById(String id) {
        return userMapper.findUserById(id);
    }
    
 // 미션 상태 업데이트 서비스
    public void updateMissionStatus(UserDTO user) {
        userMapper.updateMissionStatus(user);
    }
    
    
}