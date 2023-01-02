package com.genie.ludalantern.controller;


import com.genie.ludalantern.domain.entity.UserEntity;
import com.genie.ludalantern.dto.ResponseDTO;
import com.genie.ludalantern.dto.UserDTO;
import com.genie.ludalantern.security.TokenProvider;
import com.genie.ludalantern.service.ConnectServiceImple;
import com.genie.ludalantern.service.UserServiceImple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class UserController {
    @Autowired
    private UserServiceImple userService;

    @Autowired
    private ConnectServiceImple connectService;


    @Autowired
    private TokenProvider tokenProvider;

   private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        try{
            if(userDTO == null || userDTO.getPassword()==null){
                throw new RuntimeException("Invelid Password value");
            }


            //요청을 이용해 저장할 유저 만들기
            UserEntity user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();

            //서비스를 이용해 리포지토리에 유저 저장
            UserEntity registerUser = userService.createUser(user);

            connectService.createConnect(registerUser);

            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registerUser.getEmail())
                    .userId(registerUser.getUserId())
                    .username(registerUser.getUsername())
                    .build();
            //return ResponseEntity.ok().body(responseUserDTO);
            return ResponseEntity.ok(responseUserDTO);
        }catch (Exception e){
            //유저 정보는 항상 하나이므로 리스트가 아닌 그냥 UserDto 리턴


            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){

        UserEntity user = userService.getByCredentials(
                userDTO.getEmail(),
                userDTO.getPassword(),
                passwordEncoder);


        if(user !=null){
            //토큰 생성
            final String token = tokenProvider.create(user);

            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .userId(user.getUserId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        }else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login faild.")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }



}
