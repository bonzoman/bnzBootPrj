package com.bnz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //RestController 인 경우 default return type이 @ResponseBody 이므로 생략가능
public class Sample1RestController {

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }
}
