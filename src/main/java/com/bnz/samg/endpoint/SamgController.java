package com.bnz.samg.endpoint;


import com.bnz.samg.biz.spec.SamgBizService;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SamgController {


    @Autowired
    SamgBizService samgBizService;

    @PostMapping(value="samg/selectList")
    public List<SamgSrchResDto> selectList(@RequestBody SamgSrchReqDto reqDto) {

        SamgSrchReqVo reqVo = SamgDtoMapper.INSTANCE.srchReqDtoToReqVo(reqDto);

        List<SamgSrchResVo> resVoList = samgBizService.selectList(reqVo);

        List<SamgSrchResDto> resDtoList = SamgDtoMapper.INSTANCE.resVoListToResDtoList(resVoList);

        return resDtoList;
    }

    @PostMapping(value="samg/insert")
    public void insert(@RequestBody SamgReqDto reqDto) {

        SamgReqVo reqVo = SamgDtoMapper.INSTANCE.reqDtoToReqVo(reqDto);

        samgBizService.insert(reqVo);

    }

}
