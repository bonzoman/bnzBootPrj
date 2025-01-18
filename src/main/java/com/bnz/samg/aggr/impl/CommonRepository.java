package com.bnz.samg.aggr.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface CommonRepository<T, ID extends Serializable>  extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    // 추가적인 공통 메서드
}
