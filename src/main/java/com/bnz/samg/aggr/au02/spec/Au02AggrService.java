package com.bnz.samg.aggr.au02.spec;


import com.bnz.samg.aggr.au02.impl.Au02EntityVo;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqDto;

import java.util.List;

public interface Au02AggrService {

    public List<Au02EntityVo> selectAu02List(SamgSrchReqDto reqDto);

    public void insert(SamgReqVo reqVo);
}
