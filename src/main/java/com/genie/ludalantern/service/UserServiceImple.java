package com.genie.ludalantern.service;



import com.genie.ludalantern.domain.entity.UserEntity;
import com.genie.ludalantern.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;

@Slf4j
@Service
public class UserServiceImple implements UserService{

    @Autowired
    private UserRepository userRepository;



    @Transactional
    @Override
    public UserEntity createUser(UserEntity userEntity) {
        if(userEntity == null || userEntity.getEmail() == null ) {
            throw new RuntimeException("Invalid arguments");
        }
        if(userEntity.getPassword() == null){
            throw new RuntimeException("password must not be null");
        }
        final String email = userEntity.getEmail();
        if(userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByEmail(email);

        //mathes 메서드를 이용해 패스워드가 같은지 확인
        if(originalUser != null && encoder.matches(password,originalUser.getPassword())){
            return originalUser;
        }
        return null;
    }



}
