package com.bnz.samg.biz;


import com.bnz.samg.aggr.au01.Au01AggrService;
import com.bnz.samg.aggr.au01.Au01EntityVo;
import com.bnz.samg.aggr.au02.Au02AggrService;
import com.bnz.samg.aggr.au02.Au02EntityVo;
import com.bnz.samg.aggr.common.spec.CommonAggrService;
import com.bnz.samg.aggr.sql.SamgQuery;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SamgBizService {

    private final Au01AggrService au01AggrService;

    private final SamgQuery samgSqlMapper;

    private final Au02AggrService au02AggrService;

    private final CommonAggrService commonAggrService;

    private RedisTemplate<String, Object> redisTemplate;

    @Transactional
    public List<Au01EntityVo> selectCachedAu01List(SamgSrchReqDto reqDto) {

        List<Au01EntityVo> resDtoList = this.selectAu01List(reqDto);
        return resDtoList;
    }

    // 변경 여부 판단
    public boolean isChange(SamgSrchReqDto reqDto) {
        return true;
    }

    @Transactional
    public List<Au01EntityVo> selectAu01List(SamgSrchReqDto reqDto) {
        List<Au01EntityVo> resultList = null;
//        List<SamgQuery.SamgSrchResVo2> resultList2 = null;
//        //mybatis
//        resultList = samgSqlMapper.selectSamgList(reqDto);
//        resultList2 = samgSqlMapper.selectSamgList2(reqDto);
//
//
//        log.debug("11111111111111111111111111111");
//        CompletableFuture.runAsync(() -> {
//            log.info("[{}] async start", Thread.currentThread().getName());
//            try {
//                List<SamgSrchResVo> resultList2 = samgSqlMapper.selectSamgList(reqDto);
//                log.debug("222222222222222222222222222222");
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.debug("333333333333333333333333");
//            }
//        });
//        log.debug("4444444444444444444444444444");
//        //jpa
        resultList = au01AggrService.selectAu01List(reqDto);


//        this.getFilteredAu01Entities("lobCd", "=", "MV");


        // Entity1DTO 조회
//        Specification<Au01EntityDto> spec1 = (root, query, cb) -> cb.equal(root.get("lobCd"), "MV");
//        System.out.println(entity1Service.findAll(spec1));


        return resultList;
    }

    @Transactional
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
//        List<Au01EntityVo> au01EntityVoList = asyncService.asyncSelectAu01List();
    }


}
