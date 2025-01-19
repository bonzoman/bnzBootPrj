package com.bnz.samg.endpoint;

import lombok.Builder;

@Builder
public record SamgSrchAu02ResDto(
        String lobCd,
        String itemName,
        String itemDetlName,
        Integer seqNo,
        String itemDetlAttr01,
        String itemDetlAttr02,
        String itemDetlAttr03,
        String itemDetlAttr04,
        Integer itemDetlAttr05
) {
}
