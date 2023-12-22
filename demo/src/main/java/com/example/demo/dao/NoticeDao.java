package com.example.demo.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//XXXDao클래스는 MVC패턴에 영향을 주는 클래스는 아니다 - 디자인 패턴

@Service
// @Repository
// 모델계층 (비즈니스로직+퍼시스턴스계층)
public class NoticeDao {// 데이터 영속성 처리하는 계층 - 퍼시스턴스계층이다.
  Logger logger = LoggerFactory.getLogger(NoticeDao.class);
  @Autowired
  SqlSessionTemplate sqlSessionTemplate = null;// SqlSession-mybatis.jar-> mybatis-spring.jar

  public List<Map<String, Object>> noticeList(Map<String, Object> pmap) {// gubun:n_title, keyword:휴관
    logger.info("noticeList");
    // MyBatis에서 제공되는 클래스 이다
    List<Map<String, Object>> list = sqlSessionTemplate.selectList("noticeList", pmap);
    logger.info(list.toString());
    return list;
  }

  public int noticeInsert(Map<String, Object> pMap) {
    logger.info("noticeInsert");
    int result = 0;
    result = sqlSessionTemplate.insert("noticeInsert", pMap);
    return result;
  }

  public int noticeUpdate(Map<String, Object> pMap) {
    logger.info("noticeUpdate");
    int result = 0;
    result = sqlSessionTemplate.update("noticeUpdate", pMap);
    return result;
  }//////////// end of noticeUpdate

  public int noticeDelete(Map<String, Object> pMap) {
    logger.info("noticeDelete");
    int result = 0;
    result = sqlSessionTemplate.delete("noticeDelete", pMap);
    return result;
  }///////////// end of noticeDelete
}