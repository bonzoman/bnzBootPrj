package com.bnz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Sample2ThymeleafController {

    /**
     * 'spring-boot-starter-thymeleaf' dependency 추가
     * @param model
     * @return
     */
    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "im thymeleaf");
        return "thymeleaf";
    }
}
