package com.bnz.samg.endpoint;

import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SamgDtoMapper {

    SamgDtoMapper INSTANCE = Mappers.getMapper( SamgDtoMapper.class );

    SamgSrchReqVo reqDtoToReqVo(SamgSrchReqDto reqDto);
    List<SamgSrchResDto> resVoListToResDtoList(List<SamgSrchResVo> resVo);

}
