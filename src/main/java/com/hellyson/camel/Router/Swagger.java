package com.hellyson.camel.Router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Swagger{
    @RequestMapping("/")
    public String redirectToUi() {
        return "redirect:/webjars/swagger-ui/index.html?url=/api/swagger&validatorUrl=";
    }
}