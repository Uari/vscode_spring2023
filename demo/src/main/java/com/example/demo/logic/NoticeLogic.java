package com.example.demo.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NoticeDao;

@Service
public class NoticeLogic {
  Logger logger = LoggerFactory.getLogger(NoticeLogic.class);

  @Autowired
  NoticeDao noticeDao = null;

  // 등록일때 - select 조건검색일때
  public List<Map<String, Object>> noticeList(Map<String, Object> pmap) {
    logger.info("noticeList");
    List<Map<String, Object>> list = new ArrayList<>();
    list = noticeDao.noticeList(pmap);
    return list;
  }// end of select

  // 등록일때 - insert일떄
  public int noticInsert(Map<String, Object> pmap) {
    logger.info("noticeInsert");
    int result = 0;
    result = noticeDao.noticeInsert(pmap);
    return result;
  }// end of insert

  // 수정일떄 - update일때
  public int noticUpdate(Map<String, Object> pmap) {
    logger.info("noticUpdate");
    int result = 0;
    result = noticeDao.noticeUpdate(pmap);
    return result;
  }// end of update

  // 삭제일때 - delete일때
  public int noticDelete(Map<String, Object> pmap) {
    logger.info("noticDelete");
    int result = 0;
    result = noticeDao.noticeDelete(pmap);
    return result;
  }// end of delete
}
