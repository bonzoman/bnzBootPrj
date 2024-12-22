package com.bnz.samg.aggr.spec;

import com.bnz.samg.aggr.impl.Au01Entity;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SamgEntityMapper {

    SamgEntityMapper INSTANCE = Mappers.getMapper( SamgEntityMapper.class );

    List<SamgSrchResVo> entityListToResVoList(List<Au01Entity> entityList);

    Au01Entity reqVoToAU01(SamgReqVo reqVo);

}
