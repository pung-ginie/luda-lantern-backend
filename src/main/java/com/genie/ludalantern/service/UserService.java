package com.genie.ludalantern.service;



import com.genie.ludalantern.domain.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface UserService {
    public UserEntity createUser(final UserEntity userEntity);
    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder);

}
