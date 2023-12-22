package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.logic.NoticeLogic;

//스프링에서는 URL매핑이 4번을 디폴트로 하고있다 
//서블릿 컨테이너와는 다르게 메소드마다 URL을 지정할 수 있다.
//URL 요청 - 요청방식 - get, post(바이너리처리 - 첨부파일처리 가능), put, delete - 전송방식 차이 
//만일 @Controller가 있는 클래스에서 JSON포맷을 예외적으로 사용하고 싶다면 @ResponseBody 사용
//@RestController @Controller와 같이 컴트롤계층을 지원하는 어노테이션임 - 처리결과가 페이지가 아닌 경우 사용
//프론트계층 (Reactjs사용)과 백엔트 계층 분리 
//RequestMapping은 클래스 이름 앞에, 메소드 이름 앞에도 가능한데 최근에는 GetMapping과 PostMapping지원함에 
//따라서 대체로 클래스 선언 앞에서만 주로 사용 
//@RequestParam은 메소드의 파라미터 자리에 사용되는 어노테이션이다.
//@Service은 서비스계층(모델계층)
@Controller
@RequestMapping("/notice/*")
public class NoticeController {
  Logger logger = LoggerFactory.getLogger(NoticeController.class);

  @Autowired
  NoticeLogic noticeLogic = null; // 초기화 할때 null이지만 스프링이 필요할때 객체를 자동으로 주입해 준다

  // 전체조회 및 조건 검색일 때
  // SELECT * FROM notice WHERE qubun=? AND keyword=?
  @GetMapping("noticeList")
  public String noticeList(@RequestParam Map<String, Object> pmap, Model model) {
    logger.info("==== noticeList 호출 ====");
    // logger.info(pmap.get("gubun").toString());
    // logger.info(pmap.get("keyword").toString());

    List<Map<String, Object>> list = new ArrayList<>();
    list = noticeLogic.noticeList(pmap);
    model.addAttribute("nList", list);
    return "forward:noticeList.jsp"; // webapp아래에서 찾는다
  }

  // get방식만 단위테스트가 가능
  // insert into notice(n_no, n_title, n_content, n_writer) values(?,?,?,?)
  @PostMapping("noticeInsert")
  public String noticeInsert(@RequestParam Map<String, Object> pmap) {
    logger.info("noticeInsert 호출");
    int result = 0;
    String path = "";
    result = noticeLogic.noticInsert(pmap);
    if (result == 1) {
      path = "redirect:noticeList";
    } else {
      path = "redirect:noticeError.jsp";
    }
    return path; // 화면을 호출하는게 아니라 url을 호출한다 - 9번라인
  }

  @GetMapping("noticeUpdate")
  public String noticeUpdate(@RequestParam Map<String, Object> pmap) {
    logger.info("noticeUpdate 호출");
    int result = 0;
    result = noticeLogic.noticUpdate(pmap);
    return "redirect:noticeList.jsp"; // 화면을 호출하는게 아니라 url을 호출한다 - 9번라인
  }

  @GetMapping("noticeDelete")
  public String noticeDelete(@RequestParam Map<String, Object> pmap) {
    logger.info("noticeDelete 호출");
    int result = 0;
    result = noticeLogic.noticDelete(pmap);
    return "redirect:noticeList.jsp"; // 화면을 호출하는게 아니라 url을 호출한다 - 9번라인
  }
}
