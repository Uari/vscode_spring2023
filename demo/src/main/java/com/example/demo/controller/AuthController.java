package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.net.URLEncoder;

@Controller
@RequestMapping("/authc/*")
public class AuthController {
  Logger logger = LoggerFactory.getLogger(AuthController.class);
  //화면이지만 스프링을 경유하도록 한다
  //왜냐면 뭔가 인터셈트 하여 전처리를 해야하는 경우라면 단순한 화면을 부르는 과정도 스프링을 태운다
  @GetMapping("loginForm")
  public String loginForm(){
    logger.info("loginForm");
    return "redirect:/auth/loginForm.jsp"; //배포위치가 webapp 이다 
  }

  @PostMapping("login")
  public String login(HttpSession session,String mem_email, String mem_pw2, boolean remember, HttpServletResponse res)throws Exception{
    logger.info("login");
    logger.info(mem_email+", "+ mem_pw2+", "+remember);
    if(!loginCheck(mem_email, mem_pw2)){
      String msg = URLEncoder.encode("비밀번호 틀림", "utf-8");
      return "redirect:/loginForm?msg="+msg;
    }
    if(remember){
      //쿠키생성하기 
      Cookie c = new Cookie("mem_email", mem_email);
      //현재 바라보는 곳 - auth -> loginForm.jsp가 있는 위치 
      //성공하면 http://localhost:8000/index.jsp
      //이런경우 쿠키가 생성되는 도메인과 달라서 반드시 setPath추가할 것
      c.setPath("/");
      c.setMaxAge(60*60);
      res.addCookie(c);
    }else{
      Cookie c = new Cookie("mem_email", "");
      c.setPath("/");
      c.setMaxAge(0);
      res.addCookie(c);
    }
    session.setAttribute("nickname", mem_email);
    return "redirect:/index.jsp"; //배포위치가 webapp 이다 
  }
  @GetMapping("logout")
  public String logout(HttpSession session){
    session.invalidate();
    return "redirect:/auth/loginForm.jsp";
  }

  private boolean loginCheck(String mem_email, String mem_pw) {

    return "kiwi@hot.com".equals(mem_email)&&"123".equals(mem_pw);
  }
}
