package com.bnz.samg.aggr.au02.impl;


import com.bnz.samg.aggr.au02.spec.Au02AggrService;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqDto;
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

        // 1.기본
        resultAll = au02Repository.findByLobCd(reqDto.lobCd());

        // 2.Specification
        //조건 1개
//      Specification<Au02Entity> spec = (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("lobCd"), "MV");
        //조건 2개
        Specification<Au02Entity> spec = (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("lobCd"), "MV"),
                criteriaBuilder.like(root.get("itemName"), "Auto"));


//        Specification<Au02Entity> spec2 = SpecificationBuilder.equals("lobCd", "MV")
//                .and(SpecificationBuilder.like("itemName", "itemName"));


        resultAll = au02Repository.findAll(spec);


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
