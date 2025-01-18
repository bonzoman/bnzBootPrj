package com.bnz.samg.aggr.spec;


import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CommonAggrService<T> {
    public List<T> findByCondition(Specification<T> reqVo);
}
