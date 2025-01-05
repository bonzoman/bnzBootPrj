package com.bnz.samg.aggr.sql;


import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import lombok.Builder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SamgQuery {

	List<SamgSrchResVo> selectSamgList(SamgSrchReqVo reqVo);

	@Select("""
		<script>
		SELECT
		 LOB_CD as lobCd
		, ITEM_NAME as itemName
		, SEQ_NO AS seqNo
		, ITEM_ATTR01 AS itemAttr01
		, ITEM_ATTR02 AS itemAttr02
		, ITEM_ATTR03 AS itemAttr03
		, ITEM_ATTR04 AS itemAttr04
		, ITEM_ATTR05 AS itemAttr05
		FROM AU01
		WHERE 1=1
		<if test='lobCd != null'>
		AND LOB_CD = #{lobCd}
		</if>
		AND   ITEM_NAME = #{itemName}
		<if test='seqNo != 0'>
		AND   SEQ_NO = #{seqNo}
		</if>
		</script>
		""")
	List<SamgSrchResVo2> selectSamgList2(SamgSrchReqVo reqVo);

	@Builder
	record SamgSrchResVo2(
			String lobCd,
			String itemName,
			int seqNo,
			String itemAttr01,
			String itemAttr02,
			String itemAttr03,
			String itemAttr04,
			int itemAttr05
	) {}
}
