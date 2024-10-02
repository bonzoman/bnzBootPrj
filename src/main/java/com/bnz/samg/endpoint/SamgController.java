package com.bnz.samg.endpoint;


import com.bnz.samg.biz.spec.SamgService;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SamgController {


    @Resource
    SamgService samgService;

    @PostMapping(value="gogo")
    public List<SamgSrchResDto> selectList(@RequestBody SamgSrchReqDto reqDto) {

        SamgSrchReqVo reqVo = SamgDtoMapper.INSTANCE.reqDtoToReqVo(reqDto);

        List<SamgSrchResVo> resVoList = samgService.selectList(reqVo);

        List<SamgSrchResDto> resDtoList = SamgDtoMapper.INSTANCE.resVoListToResDtoList(resVoList);

        return resDtoList;
    }
}
