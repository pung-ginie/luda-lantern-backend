package com.genie.ludalantern.service;


import com.genie.ludalantern.domain.entity.ConnectEntity;
import com.genie.ludalantern.domain.entity.LanternEntity;

import java.util.List;

public interface LanternService {
    public List<LanternEntity> createLantern(final LanternEntity lanternEntity, final ConnectEntity connectEntity);
    public LanternEntity retrieveLanternByUserId (final String userId);
    public List<LanternEntity> retrieveLantern();
    public LanternEntity updateLantern(final LanternEntity lanternEntity );

}
