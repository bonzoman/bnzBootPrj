package com.bnz.samg.aggr.au02;

import com.bnz.samg.biz.spec.SamgReqVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface Au02EntityMapper {

    Au02EntityMapper INSTANCE = Mappers.getMapper(Au02EntityMapper.class);

    List<Au02EntityVo> entityListToResVoList(List<Au02Entity> entityList);

    Au02Entity reqVoToAU01(SamgReqVo reqVo);

    Au02Entity mmm(Au02EntityVo dto);

}
