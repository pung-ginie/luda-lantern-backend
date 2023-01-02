package com.genie.ludalantern.domain.repository;



import com.genie.ludalantern.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);
    Optional<UserEntity> findById(String id);
    Boolean existsByEmail(String email);
    UserEntity findByEmailAndPassword(String email, String password);

}
