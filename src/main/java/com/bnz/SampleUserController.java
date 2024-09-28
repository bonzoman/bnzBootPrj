package com.bnz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller //리턴타입이 rest인 경우 @ResponseBody를 명시해줘야 함
@RestController //RestController 인 경우 default return type이 @ResponseBody 이므로 생략가능
public class SampleUserController {

    /**
     * HttpMessageConverter
     */
    @PostMapping("/user")
    public User create(@RequestBody User user){
        System.out.println(1111111111);
        return null;
    }

    /**
     * HttpMessageConverter
     */
    @PostMapping("/users/create")
    public User usersCreate(@RequestBody User user){
        System.out.println(222);
        return user;
    }
}
