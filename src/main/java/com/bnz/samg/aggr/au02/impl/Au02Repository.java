package com.bnz.samg.aggr.au02.impl;

import com.bnz.samg.aggr.common.spec.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface Au02Repository extends JpaRepository<Au02Entity, Au02Entity.PK>, CommonRepository<Au02Entity, Au02Entity.PK>, JpaSpecificationExecutor<Au02Entity> {
    List<Au02Entity> findByLobCd(String lobCd);
}
