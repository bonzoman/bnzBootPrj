package com.bnz.samg.endpoint;

import io.swagger.v3.oas.annotations.media.Schema;

public record SamgReqDto(

        @Schema(description = "보종", defaultValue = "MV", allowableValues = {"mv", "게시물2"})
        String lobCd,

        @Schema(description = "Auto Items", defaultValue = "Auto01", allowableValues = {"Auto01", "Auto02"})
        String itemName,

        @Schema(description = "순번", defaultValue = "1")
        int seqNo,

        @Schema(description = "속성1", defaultValue = "attr01")
        String itemAttr01,

        @Schema(description = "속성2", defaultValue = "attr02")
        String itemAttr02,

        @Schema(description = "속성3", defaultValue = "attr03")
        String itemAttr03,

        @Schema(description = "속성4", defaultValue = "attr04")
        String itemAttr04,

        @Schema(description = "속성5", defaultValue = "555")
        int itemAttr05
) {
}
