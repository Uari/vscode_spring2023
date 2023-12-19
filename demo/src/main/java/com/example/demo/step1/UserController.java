package com.example.demo.step1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/*")
public class UserController {
  Logger logger = LoggerFactory.getLogger(UserController.class);

  @GetMapping("login")
  public String oldlogin(HttpServletRequest req){
    String mem_id = req.getParameter("mem_id");
    String mem_pw = req.getParameter("mem_pw");
    logger.info(mem_id+", "+mem_pw);
    return "redirect:/index.jsp";
  }

  @GetMapping("login2")
  //public String login(@RequestParam(name="mem_id", required=false) String mem_id, String mem_pw){
  public String login(String mem_id, String mem_pw){
    //http://localhost:8000/user/login3 ==>mem_id = null
    //http://localhost:8000/user/login3?mem_id ==> mem_id = "", js연결되는 경우 주의할 것 
    logger.info(mem_id+", "+mem_pw);
    return "redirect:/index.jsp";
  }

  @GetMapping("login4")
  //public String login(@RequestParam(name="mem_id", required=false) String mem_id, String mem_pw){
  public String login4(@RequestParam String mem_id, String mem_pw){
    //http://localhost:8000/user/login4 ==>mem_id = null //400 Bad Request - required=true이라서 에러 - 필수입력사항
    //http://localhost:8000/user/login4?mem_id ==> mem_id = "", js연결되는 경우 주의할 것 
    logger.info(mem_id+", "+mem_pw);
    return "redirect:/index.jsp";
  }

    @GetMapping("login5")
  //public String login(@RequestParam(name="mem_id", required=false) String mem_id, String mem_pw){
  public String login5(@RequestParam Map<String,Object> pmap){
    //http://localhost:8000/user/login5 ==>mem_id = null //400 Bad Request - required=true이라서 에러 - 필수입력사항
    //http://localhost:8000/user/login5?mem_id ==> mem_id = "", js연결되는 경우 주의할 것 
    logger.info(pmap.get("mem_id")+", "+pmap.get("mem_pw"));
    return "redirect:/index.jsp";
  }

  @GetMapping("loginForm")
  public String loginForm(){
    //ViewResolver
    //접두어 : /WEB-INF/views/
    //접미어 : .jsp
    ///WEB-INF/views/user/XXX.jsp
    return "user/loginForm"; 
    //POJO - upmu[0] - workname - @RequestMapping(/user/*) - 요청시 이미 알고 있다 
    //upmu[1] - 메소드이름, 화면이름이기도 하다 
    //return "redirect:./loginForm.jsp";
  }

  //ModelAndView - WEB-INF찹음, forward 유효범위, scope:request
  //url은 그대로 인데 화면은 바뀐다 
  //화면의 이름을 생략하면 메소드 이름이 화면 이름이 된다. - 스프링이 그렇게 주입한다.
  @GetMapping("loginForm2")
  public ModelAndView loginForm2(){
    logger.info("loginForm2");
    //ModelAndView mav = new ModelAndView();
    //mav.setViewName("loginForm2");
    return new ModelAndView(); //WEB-INF/views/user/loginForm2.jsp
    //return mav; 
  }
}
