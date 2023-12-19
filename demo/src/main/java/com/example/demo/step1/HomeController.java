package com.example.demo.step1;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList; 
import java.util.Map; 
import java.util.HashMap; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//스프링에서는 서블릿과 다르게 메소드 마다에 url매핑이 가능함
import org.springframework.web.bind.annotation.GetMapping;

//spring3.0 - @ResponseBody
//페이지 출력이 아닌 모든 경우에 사용가능하다 
@RestController //@Controller 다르게 응답이 page가 아니고 text/plain이다 
@RequestMapping("/step1/*")
public class HomeController {
  Logger logger = LoggerFactory.getLogger(HomeController.class);
  //스프링에서는 req.getParameter 쓰지 않고도 파라미터 자리에 넣어주는 것 만으로 담김 
  //url -> http://localhost:8000/step1/home?param=1
  //스프링에서는 클래스와 빈은 같은말 이해 <bean>
  //어떻게 이런일이 가능한가 ? - 빈관리 - spring - context.jar -> ApplicationContext
  //스프링에서 의존성 주입을 담당하니까 가능함
  //환경설정 - spring-core.jar - 환경설정 - myBatis,Hikaricp 외부 라이브러리 - IOC
  //라이브러리에는 없는 제어권을 스프링이 갖는다 
  //스프링을 사용하면 객체에 대한 라이프사이클 관리를 빼앗긴다
  @GetMapping("jsonTest") //메소드가 호출되는 매핑이름이다 
  public String jsonTest() {
    logger.info("jsonTest 호출??");
    List<Map<String, Object>> list = new ArrayList<>();
    Map<String,Object> map = new HashMap<>();
    map.put("deptno", 10);
    map.put("dname", "총무부");
    map.put("loc", "인천");
    list.add(map);
    Gson g = new Gson();
    String temp = g.toJson(list);
    return temp;
      // return "반환되는 문자열";
  }
}
/* 
컨트롤 계층이 갖는 특징 - 역할 - 가진것 
상속을 받지는 않았지만 req, res는 여전히(순수성) 사용이 가능하다 
입력(@RequestParam - HashMapBinder소개 - 지원함 ) -> 처리 -> 츨력
사람은 처리만 
 * 컨트롤 계층이라면
 * 응답페이지 처리에 대한 책임 (관심사)
 * 필요할 떄 요청객체와 응답객체를 사용할 수도 있다. - 없어진게 아니다 
 * 메소드의 파라미터 갯수의 제약이 없다 
 * 실제 처리는 하지 않는다 - 처리는 미룬다 - xxxLogic클래스가 담당함 
 */