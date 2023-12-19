package com.example.demo.step1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//스프링에서는 서블릿과 다르게 메소드마다 url매핑이 가능함
import org.springframework.web.bind.annotation.GetMapping;

//spring3.0 -@ResponseBody
@RestController // @controller 다르게 응답이page가아니고 text/plain이다
@RequestMapping("/step1/*")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    // 스프링에서는 req.getParameter쓰지않아도 파라미터자리에 넣어주는것만으로도 담김
    // url->http://localhost:8000/step1/home
    @GetMapping("home") // 메소드가 호출되는 매핑이름이다
    public String home(String param) {
        return "반환되는 문자열";
    }

}
