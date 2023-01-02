package com.genie.ludalantern.domain.repository;


import com.genie.ludalantern.domain.entity.ConnectEntity;
import com.genie.ludalantern.domain.entity.LanternEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanternRepository  extends JpaRepository<LanternEntity,Long> {


    LanternEntity findByLanternId(Long lanternId);

    List<LanternEntity> findAllByConnectEntity(ConnectEntity connectEntity);


}
