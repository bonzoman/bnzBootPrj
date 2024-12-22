package java8.vo;

import lombok.Builder;

@Builder
public record User1Dto(
        String userId,
        String userName,
        String deptCd,
        String upDeptCd
) {
    public String bbb(){
        return "aaaaaaaaaaaaa";
    }
}
