package com.bnz.samg.endpoint;


import com.bnz.samg.biz.spec.SamgSrchReqVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SamgController {

    @PostMapping(value="gogo")
    public SamgSrchResDto list(@RequestBody SamgSrchReqDto reqDto) {

        SamgSrchReqVo reqVo = SamgMapper.INSTANCE.reqDtoToReqVo(reqDto);

        System.out.println(111);
        System.out.println(111);
        System.out.println(111);
        System.out.println(222);

        return SamgSrchResDto.builder().res1("a").res2("b").res3(333).build();
    }
}
