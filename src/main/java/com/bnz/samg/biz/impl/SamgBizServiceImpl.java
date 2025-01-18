package com.bnz.samg.biz.impl;


import com.bnz.samg.aggr.impl.Au01Entity;
import com.bnz.samg.aggr.spec.CommonAggrService;
import com.bnz.samg.aggr.spec.SamgAggrService;
import com.bnz.samg.aggr.spec.SpecificationBuilder;
import com.bnz.samg.aggr.sql.SamgQuery;
import com.bnz.samg.biz.spec.SamgBizService;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SamgBizServiceImpl implements SamgBizService {

    @Autowired private SamgQuery samgSqlMapper;

    @Autowired private SamgAggrService samgAggrService;

    @Autowired private CommonAggrService<Au01Entity> commonAggrService;

    @Transactional
    public List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo) {
        List<SamgSrchResVo> resultList = null;
        List<SamgQuery.SamgSrchResVo2> resultList2 = null;
        //mybatis
        resultList = samgSqlMapper.selectSamgList(reqVo);
        resultList2 = samgSqlMapper.selectSamgList2(reqVo);


        //jpa
        resultList = samgAggrService.selectList(reqVo);


        return resultList;
    }
    public void select(){
        Specification<Au01Entity> spec = SpecificationBuilder.<Au01Entity>equals("field1", "value1")
                .and(SpecificationBuilder.<Au01Entity>greaterThan("field2", 100))
                .and(SpecificationBuilder.<Au01Entity>like("field3", "keyword"))
                ;

        Specification<Au01Entity> spec2 = (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%John%");


        commonAggrService.findByCondition(spec);
    }

    @Transactional
    public void insert(SamgReqVo reqVo) {

        samgAggrService.insert(reqVo);
    }


}
