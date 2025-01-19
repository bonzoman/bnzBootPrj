package com.bnz.samg.aggr.au01.spec;


import com.bnz.samg.aggr.au01.impl.Au01EntityVo;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqDto;

import java.util.List;

public interface Au01AggrService {

    public List<Au01EntityVo> selectAu01List(SamgSrchReqDto reqDto);

    public void insert(SamgReqVo reqVo);
}
