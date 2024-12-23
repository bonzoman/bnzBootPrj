package java8.vo;

import lombok.Builder;

@Builder
public record UserDto(
        String userId,
        String userName,
        String deptCd,
        String upDeptCd
) {
}
