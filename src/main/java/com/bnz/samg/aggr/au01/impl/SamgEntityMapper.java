package com.bnz.samg.aggr.au01.impl;

import com.bnz.samg.biz.spec.SamgReqVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SamgEntityMapper {

    SamgEntityMapper INSTANCE = Mappers.getMapper(SamgEntityMapper.class);

    List<Au01EntityVo> entityListToResVoList(List<Au01Entity> entityList);

    Au01Entity reqVoToAU01(SamgReqVo reqVo);

    Au01Entity mmm(Au01EntityVo dto);

}
