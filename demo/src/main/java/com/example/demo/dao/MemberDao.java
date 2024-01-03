package com.example.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
  Logger logger = LoggerFactory.getLogger(MemberDao.class);

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  //한번에 여러 건을 등록하는 실습 예제 입니다.
  public int fileInsert(List<Map<String, Object>> picList){
    int result = 0;
    result = sqlSessionTemplate.insert("fileInsert", picList);
    logger.info(String.valueOf(result)); //등록요청 결과 출력해 보기 

    return result;
  }

  public int memberinsert(Map<String, Object> pmap) {
    int result = 0;
    //관전포인트 - 시퀀스로 채번된 번호를 파라미터 변수인 map에 담겨 있는지 확인하기 
    result = sqlSessionTemplate.insert("memberInsert", pmap);
    //오라클 에서는 자동 채번을 해주는 시퀀스 오브젝트가 제공된다 
    //시퀀스는 에러가 발동하더라도 무조건 언제나 반드시 1증가된 값이 채번된다 
    //문제제기 - 채번된 숫자를 유지해야한다 -왜냐면 테이블이 board_master테이블에 사용된 번호를 
    //첨부파일이 등록되는 board_sub테이블에 한 번더 사용해야 합니다. - 동일한 번호를 사용해야 된다 
    //결과 - insert메소드의 리턴타입은 성공유무를 반환하는 1또는 0만 반환되는 함수
    //그런데 우리는 오라클 서버에서 채번된 숫자를 자바로 꺼내와야한다 - 파라미터에 담아준다 - myBatis
    //이 옵션이 useGeneratedKeys이 true가 되어야 한다 - 디폴트는 false임 - 꺼져있음 
    logger.info(pmap.get("mem_no").toString()); //
    return result;
  }

  public List<Map<String, Object>> memberList(Map<String, Object> rmap) {
    List<Map<String,Object>> mList = null;
    mList = sqlSessionTemplate.selectList("memberList",rmap);
    return mList;
  }

    public List<Map<String, Object>> commentList(Map<String, Object> rmap) {
    List<Map<String,Object>> cList = null;
    cList = sqlSessionTemplate.selectList("commentList",rmap);
    return cList;
  }

    public int memberUpdate(Map<String, Object> pmap) {
      logger.info("Dao : memberUpdate 호출");
      int result = 0;
      result = sqlSessionTemplate.update("memberUpdate", pmap);
      return result;
    }
}
