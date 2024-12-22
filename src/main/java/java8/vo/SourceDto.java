package java8.vo;

import lombok.Builder;

@Builder(toBuilder = true)
public record SourceDto(
        String userId,
        String userName,
        String deptCd,
        String upDeptCd
) {

}
