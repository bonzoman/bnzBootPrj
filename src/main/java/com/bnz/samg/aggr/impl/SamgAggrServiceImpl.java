package com.bnz.samg.aggr.impl;


import com.bnz.samg.aggr.spec.SamgAggrService;
import com.bnz.samg.aggr.spec.SamgEntityMapper;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SamgAggrServiceImpl implements SamgAggrService {

    @Autowired
    private Au01Repository au01Repository;

    public List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo) {

        //findAll
        List<Au01> resultAll = au01Repository.findAll();
        List<SamgSrchResVo> resVoList = SamgEntityMapper.INSTANCE.entityListToResVoList(resultAll);

        //findById
        Au01.PK pk = Au01.PK.builder().lobCd(reqVo.lobCd()).itemName(reqVo.itemName()).seqNo(reqVo.seqNo()).build();
        Au01 au01 = au01Repository.findById(pk).orElseGet(Au01::new);

        return resVoList;
    }

    public void insert(SamgReqVo reqVo) {

        Au01 au01 = SamgEntityMapper.INSTANCE.reqVoToAU01(reqVo);

        au01Repository.save(au01);

    }
}
