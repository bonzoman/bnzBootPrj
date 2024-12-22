package java8.vo;

import lombok.Builder;

@Builder(toBuilder = true)
public record TargetDto(
        String userId,
        String userName,
        String deptCd,
        String upDeptCd
) {

}
