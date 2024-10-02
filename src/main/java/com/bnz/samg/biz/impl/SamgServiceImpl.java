package com.bnz.samg.biz.impl;


import com.bnz.samg.aggr.mapper.SamgMapper;
import com.bnz.samg.biz.spec.SamgService;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SamgServiceImpl implements SamgService {

    @Autowired
    private SamgMapper samgMapper;

    public List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo) {

        List<SamgSrchResVo> resultList = samgMapper.selectSamgList(reqVo);

        return resultList;
    }
}
