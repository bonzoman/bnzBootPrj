package com.bnz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller //리턴타입이 rest인 경우 @ResponseBody를 명시해줘야 함
@RestController //RestController 인 경우 default return type이 @ResponseBody 이므로 생략가능
public class SampleUserController {

    /**
     * HttpMessageConverter
     */
    @PostMapping("/user")
    public @ResponseBody User create(@RequestBody User user){
        return null;
    }

    /**
     * HttpMessageConverter
     */
    @PostMapping("/users/create")
    public User usersCreate(@RequestBody User user){
        return user;
    }
}
