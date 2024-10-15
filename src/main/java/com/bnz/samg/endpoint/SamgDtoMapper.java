package com.bnz.samg.endpoint;

import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SamgDtoMapper {

    SamgDtoMapper INSTANCE = Mappers.getMapper( SamgDtoMapper.class );

    //selectList
    SamgSrchReqVo srchReqDtoToReqVo(SamgSrchReqDto reqDto);
    List<SamgSrchResDto> resVoListToResDtoList(List<SamgSrchResVo> resVo);

    //insert
    SamgReqVo reqDtoToReqVo(SamgReqDto reqDto);


}
