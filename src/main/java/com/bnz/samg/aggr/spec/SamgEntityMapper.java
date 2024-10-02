package com.bnz.samg.aggr.spec;

import com.bnz.samg.aggr.impl.Au01;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SamgEntityMapper {

    SamgEntityMapper INSTANCE = Mappers.getMapper( SamgEntityMapper.class );

    List<SamgSrchResVo> entityListToResVoList(List<Au01> entityList);

}
