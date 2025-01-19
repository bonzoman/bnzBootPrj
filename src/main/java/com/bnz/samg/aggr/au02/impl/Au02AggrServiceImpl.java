package com.bnz.samg.aggr.au02.impl;


import com.bnz.samg.aggr.au02.spec.Au02AggrService;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Au02AggrServiceImpl implements Au02AggrService {

    @Autowired
    private Au02Repository au02Repository;

    public List<Au02EntityVo> selectAu02List(SamgSrchReqDto reqDto) {
        List<Au02Entity> resultAll = null;

        resultAll = au02Repository.findByLobCd(reqDto.lobCd());

        List<Au02EntityVo> resVoList = Au02EntityMapper.INSTANCE.entityListToResVoList(resultAll);
        return resVoList;
    }

    /**
     * @param reqVo request
     */
    public void insert(SamgReqVo reqVo) {

        Au02Entity au02Entity = Au02EntityMapper.INSTANCE.reqVoToAU01(reqVo);

        au02Repository.save(au02Entity);

    }
}
