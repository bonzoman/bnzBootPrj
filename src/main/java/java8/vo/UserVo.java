package java8.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class UserVo {
    String userId;
    String userName;
    String deptCd;
    String upDeptCd;
}
