package java8.vo;

import lombok.Builder;

@Builder
public record UserDto(
        String userName,
        String deptCd,
        String upDeptCd
) {
}
