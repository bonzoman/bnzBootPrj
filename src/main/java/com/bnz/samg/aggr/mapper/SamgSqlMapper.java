package com.bnz.samg.aggr.mapper;

import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SamgSqlMapper {

	public List<SamgSrchResVo> selectSamgList(SamgSrchReqVo reqVo);

}
