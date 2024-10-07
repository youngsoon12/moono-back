package com.moono.controller;

import com.moono.dto.UserDTO;
import com.moono.dto.UserResponseDTO;
import com.moono.service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.moono.JWT.JwtTokenProvider;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@CrossOrigin(origins = "*") // localhost:3000에 대해 CORS 허용
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;  // JWT 토큰 발급을 위한 빈

    // 회원가입 API
    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO user) {
        userService.registerUser(user);
        return "회원가입 성공";
    }
    

 // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO user) {
        UserDTO loggedInUser = userService.login(user.getId(), user.getPwd());
        if (loggedInUser != null) {
            // 로그인 성공 시 JWT 토큰 발급
        	String token = jwtTokenProvider.generateToken(
        		    loggedInUser.getId(),
        		    loggedInUser.getNickName(),
        		    loggedInUser.isOneMission(),
        		    loggedInUser.isTwoMission(),
        		    loggedInUser.isThreeMission(),
        		    loggedInUser.isFourMission(),
        		    loggedInUser.isFiveMission()
        		);
            
            // UserDTO를 UserResponseDTO로 변환
            UserResponseDTO userResponse = new UserResponseDTO();
            userResponse.setNickName(loggedInUser.getNickName());
            userResponse.setOneMission(loggedInUser.isOneMission());
            userResponse.setTwoMission(loggedInUser.isTwoMission());
            userResponse.setThreeMission(loggedInUser.isThreeMission());
            userResponse.setFourMission(loggedInUser.isFourMission());
            userResponse.setFiveMission(loggedInUser.isFiveMission());

            // 응답 객체에 토큰과 userResponse 추가
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", userResponse);
            return ResponseEntity.ok(response); // 성공 시 200 OK와 함께 토큰 반환
        } else {
            // 로그인 실패 시 401 Unauthorized 상태 코드 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID or password");
        }
    }
    
 // 아이디 중복 확인 API
    @GetMapping("/check-id")
    public ResponseEntity<?> checkDuplicateId(@RequestParam String id) {
        UserDTO existingUser = userService.findUserById(id); // 아이디로 사용자 찾기
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다.");
        } else {
            return ResponseEntity.ok("사용 가능한 아이디입니다.");
        }
    }

    public class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
    
    // 미션 상태 업데이트 API
    @PutMapping("/update-mission")
    public ResponseEntity<String> updateMissionStatus(@RequestBody UserDTO user) {
        userService.updateMissionStatus(user);
        return ResponseEntity.ok("미션 상태 업데이트 성공");
    }
    
 // 특정 유저 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        UserDTO user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    
   
    
    
}