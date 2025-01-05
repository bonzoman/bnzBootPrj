package com.bnz.samg.endpoint;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SamgSrchReqDto(
        @Schema(example = "MV")
        String lobCd,

        @Schema(example = "Auto")
        String itemName,

        @Schema(example = "1")
        int seqNo
) {
}
