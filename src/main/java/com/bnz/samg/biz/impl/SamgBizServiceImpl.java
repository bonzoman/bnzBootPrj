package com.bnz.samg.biz.impl;


import com.bnz.samg.aggr.spec.SamgAggrService;
import com.bnz.samg.aggr.sql.SamgQuery;
import com.bnz.samg.biz.spec.SamgBizService;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SamgBizServiceImpl implements SamgBizService {

    @Autowired
    private SamgQuery samgSqlMapper;

    @Autowired
    private SamgAggrService samgAggrService;

    @Transactional
    public List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo) {
        List<SamgSrchResVo> resultList = null;
        List<SamgQuery.SamgSrchResVo2> resultList2 = null;
        //mybatis
        resultList = samgSqlMapper.selectSamgList(reqVo);
        resultList2 = samgSqlMapper.selectSamgList2(reqVo);


        //jpa
        //resultList = samgAggrService.selectList(reqVo);


        return resultList;
    }

    @Transactional
    public void insert(SamgReqVo reqVo) {

        samgAggrService.insert(reqVo);
    }


}
