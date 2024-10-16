package com.bnz.samg.biz.impl;


import com.bnz.samg.aggr.mapper.SamgSqlMapper;
import com.bnz.samg.aggr.spec.SamgAggrService;
import com.bnz.samg.biz.spec.SamgBizService;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SamgBizServiceImpl implements SamgBizService {

    @Autowired
    private SamgSqlMapper samgSqlMapper;

    @Autowired
    private SamgAggrService samgAggrService;

    public List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo) {

        //mybatis
        List<SamgSrchResVo> resultList = samgSqlMapper.selectSamgList(reqVo);

        //jpa
        resultList = samgAggrService.selectList(reqVo);


        return resultList;
    }

    public void insert(SamgReqVo reqVo) {

        samgAggrService.insert(reqVo);
    }


}
