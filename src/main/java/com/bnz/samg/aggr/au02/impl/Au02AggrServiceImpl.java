package com.bnz.samg.aggr.au02.impl;


import com.bnz.samg.aggr.au02.spec.Au02AggrService;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Au02AggrServiceImpl implements Au02AggrService {

    @Autowired
    private Au02Repository au02Repository;

    public List<Au02EntityVo> selectAu02List(SamgSrchReqDto reqDto) {
        List<Au02Entity> resultAll = null;

        //NOTE: 1.기본
        resultAll = au02Repository.findByLobCd(reqDto.lobCd());

        //NOTE: 2.Specification
        //NOTE: 조건 1개
        Specification<Au02Entity> spec1 = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("lobCd"), "MV");

        //NOTE: 조건 2개
        Specification<Au02Entity> spec2 = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("lobCd"), "MV"),   // AND lobCd = 'MV'
                criteriaBuilder.like(root.get("itemName"), "Auto")// AND itemName like '%Auto%'
        );

        //NOTE: 조건 여러개
        Specification<Au02Entity> spec3 = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate condition1 = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("lobCd"), "MV"),   // AND lobCd = 'MV'
                    criteriaBuilder.like(root.get("itemName"), "Auto")// AND itemName like '%Auto%'
            );
            Predicate condition2 = criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("itemDetlAttr01"), "a1"), //and ( ae1_0.item_detl_attr01=?
                    criteriaBuilder.equal(root.get("itemDetlAttr02"), "b2")  //   or ae1_0.item_detl_attr02=? )
            );

            return criteriaBuilder.and(condition1, condition2);
        };

        resultAll = au02Repository.findAll(spec1);
        resultAll = au02Repository.findAll(spec2);
        resultAll = au02Repository.findAll(spec3);

        List<Au02EntityVo> resVoList = Au02EntityMapper.INSTANCE.entityListToResVoList(resultAll);
        return resVoList;
    }

    /**
     * @param reqVo request
     */
    public void insert(SamgReqVo reqVo) {

        Au02Entity au02Entity = Au02EntityMapper.INSTANCE.reqVoToAU01(reqVo);

        au02Repository.save(au02Entity);

    }
}
