package com.genie.ludalantern.service;


import com.genie.ludalantern.domain.entity.ConnectEntity;
import com.genie.ludalantern.domain.entity.UserEntity;

public interface ConnectService {
    public ConnectEntity createConnect(final UserEntity userEntity);

    public ConnectEntity retrieveConnect(String userId);
}
