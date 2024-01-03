package com.example.demo.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;

@Service
public class MemberLogic {
  Logger logger = LoggerFactory.getLogger(MemberLogic.class);

  @Autowired
  private MemberDao memberDao;

  public int memberInsert(Map<String, Object> pmap) {
    logger.info("memberInsert Dao호출");
    int result = memberDao.memberinsert(pmap);
    Map<String, Object> pmap2 = new HashMap<>();
    if (result == 1) {
      int mem_no = 0;
      if (pmap.containsKey("mem_no")) {
        mem_no = Integer.parseInt(pmap.get("mem_no").toString());
        System.out.println("mem_no ===>" + mem_no);
        pmap2.put("mem_no", mem_no);
      }
      // 파일 추가 테이블에 insert하기

      // memberDao.fileinsert(pmap);//mem_no
      // fileDao.fileInsert(pmap2);
    }
    return result;
  }

  public int fileInsert() {
    int result = 0;
    List<Map<String, Object>> picList = new ArrayList<>();
    Map<String, Object> pmap = new HashMap<>();
    pmap.put("file_name", "a.png");
    pmap.put("file_size", 1.5);
    picList.add(pmap);
    pmap = new HashMap<>();
    pmap.put("file_name", "b.png");
    pmap.put("file_size", 2.5);
    picList.add(pmap);
    pmap = new HashMap<>();
    pmap.put("file_name", "c.png");
    pmap.put("file_size", 3.5);
    picList.add(pmap);

    result = memberDao.fileInsert(picList);
    return result;
  }

  public int memberDelete(int mem_no) {
    logger.info("memberDelete");
    int result = 0;

    return result;
  }

  // Logic클래스를 꼭 구현해야 하나요? - 업무적인 복잡도가 낮아서
  // 정규식 - java.util.regex.*
  // pointcut express * com.example.demo.*Logic.cud*(..) - 간섭 - 코드 추가함 - 자동으로 -
  // 일괄처리
  // @Transactional
  public List<Map<String, Object>> memberList(Map<String, Object> rmap) {
    List<Map<String, Object>> mList = null;
    // before advice con.setAutoCommit(false);
    mList = memberDao.memberList(rmap);

    // after advice con.commit();
    // con.setAutoCommit(true);
    return mList;
  }

  public List<Map<String, Object>> memberDetail(Map<String, Object> rmap) {
    List<Map<String, Object>> mList = null;
    // before advice con.setAutoCommit(false);
    mList = memberDao.memberList(rmap);
    /*
     * List<Map<String,Object>> cList = null;
     * cList = memberDao.comentList(rmap);
     * Map<String,Object> rmap = new HashMap<>();
     * rmap.put(1,cList);
     * mList.add(rmap);
     * [{1건},
     * {comments:[
     * {1건}, {1건}, {1건}
     * ]}
     * ]
     */

    // after advice con.commit();
    // con.setAutoCommit(true);
    return mList;
  }

  public int memberUpdate(Map<String, Object> pmap) {
    logger.info("Logic : memberUpdate 호출");
    int result = memberDao.memberUpdate(pmap);
    
    return result;
  }
}