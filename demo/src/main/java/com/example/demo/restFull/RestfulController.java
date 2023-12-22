package com.example.demo.restFull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import com.vo.MemberVO;

//React UI
@RestController
@RequestMapping("/http/*")
public class RestfulController {
  Logger logger = LoggerFactory.getLogger(RestfulController.class);

  // 테스트 방법
  // http://localhost:8000/http/get?mem_id=kiwi&mem_name=키워
  @GetMapping("get")
  public String getTest(MemberVO mVO) {
    return "get요청 : " + mVO.getMem_id() + ", " + mVO.getMem_name();
  }

  // http://localhost:8000/http/lombokTest?mem_no=3&mem_id=apple
  @GetMapping("lombokTest")
  public String lombokTest(MemberVO mVO) {
    MemberVO mVO2 = MemberVO.builder().mem_id(mVO.getMem_id()).mem_no(5).build();
    return "lombok테스트 : " + mVO2.getMem_id() + ", " + mVO2.getMem_name() + "||" + mVO2.getMem_no();
  }

  @PostMapping("post")
  public String postTest(@RequestBody MemberVO mVO) {
    return "post요청 : " + mVO.getMem_id() + ", " + mVO.getMem_name();
  }

  @PostMapping("post1")
  public String postTest(@RequestBody Map<String, Object> pMap) {

    return "post요청 : " + pMap.get("mem_id") + ", " + pMap.get("mem_name");
  }

  // http://localhost:8000/http/update/7
  @PutMapping("update/{id}")
  public MemberVO update(@PathVariable int id) {
    logger.info(String.valueOf(id)); // 7?
    return null;
  }

  // http://localhost:8000/http/delete/7
  // @PathVariable 값은 상세조회시 나 삭제시 pk값으로 사용됨
  @DeleteMapping("delete/{no}")
  public String delelte(@PathVariable int no) {
    logger.info(String.valueOf(no)); // 7?
    return String.valueOf(no);
  }
}
/*
 * 1. Get 요청(select시 사용)
 * : 주소에 데이터를 담아 보낸다
 * : 데이터 형태는 key=value이다
 * 
 * 2. Post, Put, Delete 요청
 * : Body에 담아서 보낸다 , 데이터 형태는 json으로 통일
 * : form태그 요청은 GET요청과 POST요청만 가능함
 * : put, delete는 자바스크립트로 요청함 - 테스트 번거로움
 * 
 * 템플릿엔진, 리엑트
 * 자바스크립트 ajax, fetch, axios - 비동기 처리 지원 api
 * 자바스크립트는 기본적으로 동기 (setTimeout)
 */
