package com.bnz.samg.aggr.au02.impl;


import lombok.Builder;

@Builder
public record Au02EntityVo(
        String lobCd,
        String itemName,
        String itemDetl,
        Integer seqNo,
        String itemDetlAttr01,
        String itemDetlAttr02,
        String itemDetlAttr03,
        String itemDetlAttr04,
        Integer itemDetlAttr05
) {
}
