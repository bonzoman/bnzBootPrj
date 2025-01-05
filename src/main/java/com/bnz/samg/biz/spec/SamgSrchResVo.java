package com.bnz.samg.biz.spec;

import lombok.Builder;

@Builder
public record SamgSrchResVo(
        String lobCd,
        String itemName,
        int seqNo,
        String itemAttr01,
        String itemAttr02,
        String itemAttr03,
        String itemAttr04,
        int itemAttr05
) {
}
