package com.bnz.samg.endpoint;

import com.bnz.samg.aggr.au01.impl.Au01EntityVo;
import com.bnz.samg.aggr.au02.impl.Au02EntityVo;
import com.bnz.samg.biz.spec.SamgReqVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SamgDtoMapper {

    SamgDtoMapper INSTANCE = Mappers.getMapper(SamgDtoMapper.class);

    List<SamgSrchAu01ResDto> au01EntityVo_To_ResDto(List<Au01EntityVo> resDto);

    List<SamgSrchAu02ResDto> au02EntityVo_To_ResDto(List<Au02EntityVo> resDto);

    //insert
    SamgReqVo reqDtoToReqVo(SamgReqDto reqDto);


}
