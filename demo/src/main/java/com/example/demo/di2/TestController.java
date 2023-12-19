package com.example.demo.di2;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;

//@Controller //@RestController  -  getBean() 호출안되어도 미리 다 생성되어 있다 
public class TestController {
  Logger logger = LoggerFactory.getLogger(TestController.class);
  
  //@Autowired
  TestLogic testLogic = null;
  //setter 객체주입법 코드이다.
  public void setTestLogic(TestLogic testLogic){
    this.testLogic = testLogic;
  }

  public String testList(){
    List<Map<String, Object>> list = null;
    list = testLogic.testList();
    logger.info("List : "+list.toString());
    return "OK";
  }
}
