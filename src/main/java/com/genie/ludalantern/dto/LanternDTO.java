package com.genie.ludalantern.dto;

import com.genie.ludalantern.domain.entity.ConnectEntity;
import com.genie.ludalantern.domain.entity.LanternEntity;
import lombok.*;

@Builder
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class LanternDTO {
    private Long lanternId;
    private int lantetnNumber;
    private String wish;
    private boolean isPublic;
    private ConnectEntity connectEntity;

    private Long connectId;

    public LanternDTO(final LanternEntity lanternEntity){
        this.lanternId = lanternEntity.getLanternId();
        this.lantetnNumber = lanternEntity.getLantetnNumber();
        this.wish = lanternEntity.getWish();
        this.isPublic = lanternEntity.isAvtive();
        //this.connectEntity = lanternEntity.getConnectEntity();
        this.connectId= lanternEntity.getConnectEntity().getConnectId();
    }

    public  static LanternEntity toLanternEntity(final LanternDTO lanternDTO){
        return LanternEntity.builder().
                lanternId(lanternDTO.getLanternId()).
                lantetnNumber(lanternDTO.getLantetnNumber()).
                wish(lanternDTO.getWish()).
                avtive(lanternDTO.isPublic()).
                connectEntity(lanternDTO.getConnectEntity()).
                build();
    }


}
