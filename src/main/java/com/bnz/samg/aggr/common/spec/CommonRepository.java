package com.bnz.samg.aggr.common.spec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    // 추가적인 공통 메서드
//    default boolean existsByCondition(Specification<T> specification) {
//        return findOne(specification).isPresent();
//    }
}
