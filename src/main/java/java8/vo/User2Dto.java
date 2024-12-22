package java8.vo;

import lombok.Builder;

@Builder
public record User2Dto(
        String userId,
        String userName,
        String deptCd,
        String upDeptCd,
        String jsonData
) {
}
