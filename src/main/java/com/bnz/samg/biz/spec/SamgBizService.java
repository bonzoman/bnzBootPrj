package com.bnz.samg.biz.spec;


import java.util.List;

public interface SamgBizService {

    List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo);
    void insert(SamgReqVo reqVo);
}
