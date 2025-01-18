package com.bnz.samg.aggr.impl;


import com.bnz.samg.aggr.spec.CommonAggrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonAggrServiceImpl<T> implements CommonAggrService<T> {


    @Autowired
    private CommonRepository<T, ?> commonRepository;

    @Override
    public List<T> findByCondition(Specification<T> specification) {
        return commonRepository.findAll(specification);
    }

}
