package com.bnz.samg.biz.spec;


import com.bnz.samg.aggr.au01.impl.Au01EntityVo;
import com.bnz.samg.aggr.au02.impl.Au02EntityVo;

import java.util.List;

public interface SamgBizService {

    List<Au01EntityVo> selectAu01List(SamgSrchReqDto reqDto);

    List<Au02EntityVo> selectAu02List(SamgSrchReqDto reqDto);

    void insert(SamgReqVo reqVo);
}
