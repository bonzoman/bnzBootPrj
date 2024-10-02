package com.bnz.samg.biz.spec;


import java.util.List;

public interface SamgBizService {

    public List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo);
}
