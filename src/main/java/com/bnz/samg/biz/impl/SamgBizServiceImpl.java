package com.bnz.samg.biz.impl;


import com.bnz.samg.aggr.au01.impl.Au01EntityVo;
import com.bnz.samg.aggr.au01.spec.Au01AggrService;
import com.bnz.samg.aggr.au02.impl.Au02EntityVo;
import com.bnz.samg.aggr.au02.spec.Au02AggrService;
import com.bnz.samg.aggr.common.spec.CommonAggrService;
import com.bnz.samg.aggr.sql.SamgQuery;
import com.bnz.samg.biz.spec.SamgBizService;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SamgBizServiceImpl implements SamgBizService {

    @Autowired
    private SamgQuery samgSqlMapper;

    @Autowired
    private Au01AggrService au01AggrService;

    @Autowired
    private Au02AggrService au02AggrService;

    @Autowired
    private CommonAggrService commonAggrService;


    @Transactional
    @Override
    public List<Au01EntityVo> selectAu01List(SamgSrchReqDto reqDto) {
        List<Au01EntityVo> resultList = null;
//        List<SamgQuery.SamgSrchResVo2> resultList2 = null;
//        //mybatis
//        resultList = samgSqlMapper.selectSamgList(reqDto);
//        resultList2 = samgSqlMapper.selectSamgList2(reqDto);
//
//
//        //jpa
        resultList = au01AggrService.selectAu01List(reqDto);

//        this.getFilteredAu01Entities("lobCd", "=", "MV");


        // Entity1DTO 조회
//        Specification<Au01EntityDto> spec1 = (root, query, cb) -> cb.equal(root.get("lobCd"), "MV");
//        System.out.println(entity1Service.findAll(spec1));


        return resultList;
    }

    @Transactional
    @Override
    public List<Au02EntityVo> selectAu02List(SamgSrchReqDto reqDto) {
        List<Au02EntityVo> resultList = null;

        //        //jpa
        resultList = au02AggrService.selectAu02List(reqDto);


        return resultList;
    }

    public void select() {
//        Specification spec = SpecificationBuilder.equals("field1", "value1")
//                .and(SpecificationBuilder.greaterThan("field2", 100))
//                .and(SpecificationBuilder.like("field3", "keyword"));
//
//        Specification spec2 = (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%John%");
//
//        commonAggrService.findByCondition(spec);
    }

//    public List<Au01EntityVo> getFilteredAu01Entities(String key, String operation, Object value) {
//        // DTO 기반으로 Specification 생성
//        SpecificationBuilder<Au01EntityVo> specificationBuilder = new SpecificationBuilder<>();
//        Specification<Au01EntityVo> specification = specificationBuilder.with(key, operation, value).build();
//
//        // CommonAggrService에서 DTO 처리
////        CommonAggrService commonAggrService = new CommonAggrServiceImpl();
//        List list = commonAggrService.findByCondition(Au01EntityVo.class, specification);
//        return list;
//    }


    @Transactional
    public void insert(SamgReqVo reqVo) {

        au01AggrService.insert(reqVo);
    }


}
