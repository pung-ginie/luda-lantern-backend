package com.genie.ludalantern.controller;


import com.genie.ludalantern.domain.entity.ConnectEntity;
import com.genie.ludalantern.domain.entity.LanternEntity;
import com.genie.ludalantern.dto.LanternDTO;
import com.genie.ludalantern.dto.ResponseDTO;
import com.genie.ludalantern.service.ConnectServiceImple;
import com.genie.ludalantern.service.LanternServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/lantern")
public class LanternController {
    public final LanternServiceImple lanternService;

    @Autowired
    private ConnectServiceImple connectService;

    @Autowired
    public LanternController(LanternServiceImple lanternService) {
        this.lanternService = lanternService;
    }

    @PostMapping()
    public ResponseEntity<?> createLantern(@AuthenticationPrincipal String userId, @RequestBody LanternDTO lanternDTO){
        try{
            ConnectEntity connectEntity = connectService.retrieveConnect(userId);
            LanternEntity lanternEntity = LanternDTO.toLanternEntity(lanternDTO);
            lanternEntity.setConnectEntity(connectEntity);
            //String userId = "1";
           // List<LanternEntity> entities

            List<LanternEntity> lanternEntities = lanternService.createLantern(lanternEntity,connectEntity);


            List<LanternDTO> dtos = lanternEntities.stream().map(LanternDTO::new).collect(Collectors.toList());
            ResponseDTO<LanternDTO> response = ResponseDTO.<LanternDTO>builder().data(dtos).build();

            return  ResponseEntity.ok().body(response);



        }catch (Exception e){
            // 혹시 예외가 나는 경우 dto 대신 메세지 넣어서 리턴

            String error = e.getMessage();
            ResponseDTO<LanternDTO> response = ResponseDTO.<LanternDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);

            // return new ResponseEntity<ScheduleDTO>(HttpStatus.UNAUTHORIZED);
        }

    }

}
