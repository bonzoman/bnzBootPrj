package com.bnz.samg.endpoint;

import lombok.Builder;

@Builder
public record SamgSrchResDto(
        String res1,
        String res2,
        int res3
) {
}
