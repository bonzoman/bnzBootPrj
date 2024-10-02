package com.bnz.samg.endpoint;

public record SamgSrchReqDto(
        String lobCd,
        String itemName,
        int seqNo
) {
}
