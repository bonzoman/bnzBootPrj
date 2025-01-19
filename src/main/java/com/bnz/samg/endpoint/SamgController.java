package com.bnz.samg.endpoint;


import com.bnz.samg.aggr.au01.impl.Au01EntityVo;
import com.bnz.samg.aggr.au02.impl.Au02EntityVo;
import com.bnz.samg.biz.spec.SamgBizService;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SamgController {


    @Autowired
    SamgBizService samgBizService;

    @PostMapping(value = "samg/selectAu01List")
    public List<SamgSrchAu01ResDto> selectAu01List(@RequestBody SamgSrchReqDto reqDto) {
        List<Au01EntityVo> resVoList = samgBizService.selectAu01List(reqDto);
        List<SamgSrchAu01ResDto> resDtoList = SamgDtoMapper.INSTANCE.au01EntityVo_To_ResDto(resVoList);
        return resDtoList;
    }

    @PostMapping(value = "samg/selectAu02List")
    public List<SamgSrchAu02ResDto> selectAu02List(@RequestBody SamgSrchReqDto reqDto) {
        List<Au02EntityVo> resVoList = samgBizService.selectAu02List(reqDto);
        List<SamgSrchAu02ResDto> resDtoList = SamgDtoMapper.INSTANCE.au02EntityVo_To_ResDto(resVoList);
        return resDtoList;
    }


    @PostMapping(value = "samg/insert")
    public void insert(@RequestBody SamgReqDto reqDto) {

        SamgReqVo reqVo = SamgDtoMapper.INSTANCE.reqDtoToReqVo(reqDto);

        samgBizService.insert(reqVo);

    }

}
