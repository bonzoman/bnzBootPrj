package com.bnz.samg.endpoint;

import com.bnz.samg.biz.spec.SamgSrchReqVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SamgMapper {
    SamgMapper INSTANCE = Mappers.getMapper( SamgMapper.class );

    SamgSrchReqVo reqDtoToReqVo(SamgSrchReqDto reqDto);

}
