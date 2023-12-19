package com.example.demo.di2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//xml 문서 대신 자바 클래스를 통해서 필요한 객체를 미리 등록해 줌
//이러면 이른 객체 생성을 가져갈 수 있음 
@Configuration
//스프링에서 해당 클래스를 관리함 - 스캔 - 클래스 정보 수집 
// ApplicationContext 컨테이너는 이렇게 등록된 빈을 관리해 줌 
public class MyConfig {

  @Bean public TestController testController(){//메소드를 통해서 객체를 주입받는 방법 - 권장방식임 
    return new TestController();
  }

  @Bean public TestLogic testLogic(){
    return new TestLogic();
  }

  @Bean public TestDao testDao(){
    return new TestDao();
  }
}
