package com.bnz.samg.aggr.impl;


import com.bnz.samg.aggr.spec.SamgAggrService;
import com.bnz.samg.aggr.spec.SamgEntityMapper;
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

        List<Au01> resultAll = au01Repository.findAll();

        List<SamgSrchResVo> resVoList = SamgEntityMapper.INSTANCE.entityListToResVoList(resultAll);

        return resVoList;
    }
}
