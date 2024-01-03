package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* 첨부파일 처리와 이미지 파일 처리 , 다운로드 처리 추가 
 * 응답이 페이지가 아닐 때 선택함
 * 응답이 어떤 mime타입일 때 인가? 1) text/plain - 단순한 문자열, 2)application/json - JSON 포맷
 * @RestController에 선언하는 메소드의 리턴타입은 String 이다
 * 주의사항 - 절대로 redirect나 forward를 붙이지 말 것
 * 세션을 주입 받을 수 있다 - 상태관리(쿠키-로컬, 세션-서버-cache메모리)
 */

@RestController
@RequestMapping("/member/*")
public class RestMemberController {
  Logger logger = LoggerFactory.getLogger(RestController.class);

  @GetMapping("imageGet")
  public String imageGet(){
    logger.info("imageGet");

    return"imageGet";
  }

  @GetMapping("imageUpload")
  public String imageUpload(){
    logger.info("imageUpload");

    return"imageUpload";
  }

  @GetMapping("fileUpload")
  public String fileUpload(){
    logger.info("fileUpload");

    return"fileUpload";
  }

  @GetMapping("imageDownLoad")
  public String imageDownLoad(){
    logger.info("imageDownLoad");
    
    return"imageDownLoad";
  }
}
