package com.bnz.samg.aggr.au01;

import lombok.Builder;

@Builder
public record Au01EntityVo(
        String lobCd,
        String itemName,
        Integer seqNo,
        String itemAttr01,
        String itemAttr02,
        String itemAttr03,
        String itemAttr04,
        Integer itemAttr05
) {
}
