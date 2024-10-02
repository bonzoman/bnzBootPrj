package com.bnz.samg.biz.spec;


import java.util.List;

public interface SamgService {

    public List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo);
}
