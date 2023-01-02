package com.genie.ludalantern.domain.repository;


import com.genie.ludalantern.domain.entity.ConnectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConnectRepository  extends JpaRepository<ConnectEntity,Long> {


    ConnectEntity findByUserId(String userId);




}
