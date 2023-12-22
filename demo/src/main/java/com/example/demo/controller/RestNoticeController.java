package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
  // SELECT * FROM notice WHERE qubun=? AND keyword=?
  @GetMapping("jsonNoticeList")
  public String noticeList(@RequestParam Map<String, Object> pmap) {
    logger.info("==== noticeList 호출 ====");
    logger.info(pmap.get("gubun").toString());
    logger.info(pmap.get("keyword").toString());

    List<Map<String, Object>> list = new ArrayList<>();
    list = noticeLogic.noticeList(pmap);
    Gson g = new Gson();
    String temp = g.toJson(list);
    return temp; // webapp아래에서 찾는다
  }

}
