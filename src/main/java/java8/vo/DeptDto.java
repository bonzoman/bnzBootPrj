package java8.vo;

import lombok.Builder;

@Builder
public record DeptDto(
        String deptCd,
        String deptNm,
        String upDeptCd,
        String upDeptNm
) {
}
