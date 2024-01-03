package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.logic.NoticeLogic;
import com.google.gson.Gson;

@RestController // 화면없이 조회결과를 문자열 포맷으로 처리할 때 사용 - @ResponseBody 대체로 제공됨
@RequestMapping("/notice/*")
public class RestNoticeController {
  Logger logger = LoggerFactory.getLogger(NoticeController.class);

  @Autowired
  NoticeLogic noticeLogic = null; // 초기화 할때 null이지만 스프링이 필요할때 객체를 자동으로 주입해 준다

  // 전체조회 및 조건 검색일 때

  // Get방식으로 사용자가 입력한 값을 받을땐 - @RequestParam사용
  // post방식으로 받을 땐 - @RequestBody 사용 - 리액트로 화면을 처리 할 수있음 .
  @GetMapping("jsonNoticeList")
  public String jsonNoticeList(@RequestParam Map<String, Object> pmap) {
    logger.info("==== noticeList 호출 ====");
    List<Map<String, Object>> list = new ArrayList<>();
    list = noticeLogic.noticeList(pmap);
    Gson g = new Gson();
    String temp = g.toJson(list); // 파라미터로 받은 List<Map<>>형태를 JSON형식으로 전환해줌
    logger.info(temp);
    return temp; // webapp아래에서 찾는다
  }

  @PostMapping("noticeInsert2")
  public String noticeInsert2(@RequestBody Map<String, Object> pmap) {
    logger.info("noticeInsert2 호출");
    logger.info(pmap.toString());
    int result = 0;
    result = noticeLogic.noticInsert(pmap);
    return String.valueOf(result); // 입력이 성공 : 1, 실패 : 2
  }

//@Controller를 사용할 때와는 다르게 (화면이 아니라) JSON 형식의 데이터 셋으로 내보내야 할때
//꼭 React뿐 아니라 다른 솔루션이더라도 데이터 셋을 json으로 사용하고 있다면 모두에 적용이 가능한 공통 코드이다. 
  @GetMapping("jsonNoticeDetail")
  public String jsonNoticeDetail(@RequestParam Map<String, Object> pmap) {
    logger.info("==== jsonNoticeDetail 호출 ====");
    List<Map<String, Object>> nList = new ArrayList<>();
    nList = noticeLogic.noticeList(pmap);
    Gson g = new Gson();
    String temp = g.toJson(nList); // 파라미터로 받은 List<Map<>>형태를 JSON형식으로 전환해줌
    logger.info(temp);
    return temp; // webapp아래에서 찾는다
  }
}
