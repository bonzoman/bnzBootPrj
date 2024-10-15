package com.bnz.samg.aggr.spec;


import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;

import java.util.List;

public interface SamgAggrService {

    public List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo);

    public void insert(SamgReqVo reqVo);
}
