package com.genie.ludalantern.service;


import com.genie.ludalantern.domain.entity.ConnectEntity;
import com.genie.ludalantern.domain.entity.UserEntity;
import com.genie.ludalantern.domain.repository.ConnectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConnectServiceImple implements ConnectService {
    private final ConnectRepository connectRepository;

    @Autowired
    public ConnectServiceImple(ConnectRepository connectRepository) {
        this.connectRepository = connectRepository;
    }

    @Override
    public ConnectEntity createConnect(UserEntity userEntity) {

       ConnectEntity connectEntity = new ConnectEntity();

       connectEntity.setLanternCount(0);
       connectEntity.setUserId(userEntity.getUserId());
       connectRepository.save(connectEntity);
        return null;
    }

    @Override
    public ConnectEntity retrieveConnect(String userId) {
        ConnectEntity connectEntity = connectRepository.findByUserId(userId);
        return connectEntity;
    }
}
