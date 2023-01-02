package com.genie.ludalantern.service;


import com.genie.ludalantern.domain.entity.ConnectEntity;
import com.genie.ludalantern.domain.entity.LanternEntity;
import com.genie.ludalantern.domain.repository.ConnectRepository;
import com.genie.ludalantern.domain.repository.LanternRepository;
import com.genie.ludalantern.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LanternServiceImple implements LanternService {
    
    private final LanternRepository lanternRepository;
    private  final UserRepository userRepository;

    private  final ConnectRepository connectRepository;

    @Autowired
    public LanternServiceImple(LanternRepository lanternRepository, UserRepository userRepository, ConnectRepository connectRepository) {
        this.lanternRepository = lanternRepository;
        this.userRepository = userRepository;
        this.connectRepository = connectRepository;
    }

    @Override
    public List <LanternEntity> createLantern(LanternEntity lanternEntity, ConnectEntity connectEntity) {
        //랜턴 개수 체크 , 소원 확인
       // ConnectEntity connectEntity = connectRepository.findByUserId(userId);
        lanternCntHandler(connectEntity);
        checkAvailable(lanternEntity);



        lanternEntity.setConnectEntity(connectEntity);

        //레포지토리에 저장
        lanternRepository.save(lanternEntity);

        List<LanternEntity> entities = connectEntity.getLanternEntities();
        entities.add(lanternEntity);
        connectEntity.setLanternEntities(entities);


        log.info("lantern id : {} is saved",lanternEntity.getLanternId());
        return lanternRepository.findAllByConnectEntity(connectEntity);
        
    }

    @Override
    public LanternEntity retrieveLanternByUserId(String userId) {
        return null;
    }

    //해당 유저의 랜턴 전체 조회
    @Override
    public List<LanternEntity> retrieveLanternsByConnectId(final String userId) {

        ConnectEntity connectEntity = connectRepository.findByUserId(userId);
        List<LanternEntity> lanternEntities = connectEntity.getLanternEntities();

        if(lanternEntities == null){
            log.warn("have no lantern");
        }
        return lanternEntities;
    }




    //랜턴 전체
    // 조회 (공개 설정 한거만)
    @Override
    public List<LanternEntity> retrieveAllLantern() {
        //ConnectEntity connectEntity = connectRepository.findByUserId(userId);
        List<LanternEntity> publicLantern = lanternRepository.findByAvtive(true);
        return publicLantern;
    }



    @Override
    public LanternEntity updateLantern(LanternEntity lanternEntity) {

        checkAvailable(lanternEntity);

        final Optional<LanternEntity> original = lanternRepository.findById(lanternEntity.getLanternId());
        original.ifPresent(lantern -> {
            // (3) 반환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덮어 씌운다.
            lantern.setLanternId(lanternEntity.getLanternId());
            lantern.setAvtive(lanternEntity.isAvtive());
            lantern.setLantetnNumber(lanternEntity.getLantetnNumber());
            lantern.setWish(lanternEntity.getWish());
            lantern.setConnectEntity(lanternEntity.getConnectEntity());
            lanternRepository.save(lantern);

        });

        return lanternEntity;


    }
    
    private void lanternCntHandler(final ConnectEntity connectEntity){
        if(connectEntity.getLanternCount() >=5 ){
            log.warn("lanternEntity is allowed only five");
            throw  new RuntimeException("lanternEntity is allowed only five");
        }
        else{
            int cnt = connectEntity.getLanternCount();
            connectEntity.setLanternCount(++cnt);
        }
    }
    
    private void checkAvailable(final LanternEntity lanternEntity){
        if(lanternEntity.getWish()==null){
            log.warn("wish can not be null");
            throw  new RuntimeException("wish can not be null");
        }

    }


}
