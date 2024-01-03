package com.example.demo.controller;

import java.util.Map;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.logic.MemberLogic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class MemberController {
  Logger logger = LoggerFactory.getLogger(MemberController.class);
  //클래스의 관계 = @Autowired
  @Autowired
  private MemberLogic memberLogic;
  @GetMapping("fileInsert")
  public String fileInsert(){
    int result = 0;
    result = memberLogic.fileInsert();
    return "redirect:/index.jsp";
  }
  /************************************************************************************************** 
    제목 : 회원 가입 구현 
    작성자 : 이순신 
    작성일자 : 2023년 .....
    @param : Map
    @return : int(1:입력성공, 0이면 입력 실패)
    참조 : 화면정의서를 참고하여 구현 
    개발패턴 : jsp - action(insert) - action(select) - jsp
    테이블 : 1-n관계에 있으면서 2개 이상의 테이블에 insert처리할 때? - 업무적인 depth가 깊다 
    SQL쿼리문 : 
    insert into member2023(mem_no, mem_id, mem_pw, mem_email, mem_name, zipcode, address, gubun)
    values(seq_member2023_no.nextval, 'kiwi','123', 'kiwi@hot.com', '키위', '12345', '서울시 마포구',0);

    ************************************************************************************************/
    @PostMapping("memberInsert")
    public String memberInsert(HttpServletRequest req,@RequestParam("mem_picture") MultipartFile mem_picture, @RequestParam Map<String,Object> pmap){
    logger.info("memberInsert 호출");
    logger.info(mem_picture.toString());
    int result = -1;
    String path = "";
    
    String savePath = req.getSession().getServletContext().getRealPath("/pds");
    String mempicture = mem_picture.getOriginalFilename();
    if(mem_picture != null && mem_picture.toString().length()>2){
      //mempicture =
    }
    logger.info("mempicture" + mempicture);
    //첨부파일이 존재할 때 추가될 코드 - 첨부파일이 없다면 실행되지 않는 구간 
    if(mem_picture != null){
      logger.info("mem_picture null이 아닐때");
      String fullPath = savePath + "\\" + mempicture;
      //파일 이름을 객체로 만들어 주는 클래스이다 - 파일 이름만 생성되지 내용은 미포함 
      File file = new File(fullPath);
      try {
      byte[] bytes = mem_picture.getBytes();
      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
      bos.write(bytes); //이름만 생성된 파일에 내용을 쓰기 처리 
      bos.close();
      long size = file.length();
      double d_size = Math.floor(size/(1024.0));
      pmap.put("mem_picture", mempicture);    
      // pmap.put("file_size", d_size);    
      } catch (Exception e) {
        e.printStackTrace();
      }

    }//end of if
    //insert here 
    result = memberLogic.memberInsert(pmap);
    if(result == 1){
      path = "redirect:/member/memberList";
    }else{
      path = "redirect:/memberError.jsp";
    }
    return path;
  }/////////  end of memberInsert  ////////
  
  /*************************************************************************************************
    제목: 회원 목록 구현
    @param : 조건 검색에 사용될 컬럼명 키워드 받아오기
    @return : n건이 조회되면 List<Map>, List<Member2023>
    Map과 xxxVO의 사용 기준이 있나요? - 타입 - 계산식 
    :size에 제한이 없다 - NumberFormatException, CastException 대상임 
    :제한이 있다 . 컬럼을 매번 추가해줘야 한다. - 타입이 정해져 있다 형전환 코드 필요 없다 
    select mem_no, mem_id, mem_pw, mem_name, zipcode, address, mem_email, gubun
    from member2023
    where mem_id =: x
      and  address Like '%'||:y||'%'; 
    데이터의 영속성을 활용한 결과값들은 viewModel에 꼭 필요하다 - 클래스 이름 추가할 때 
    화면(<table:로우+컬럼>)과 데이터는 밀접한 관계가 있다 . 
    DefaultTableModel, JTable
    하이브리트웹 = 웹 + 앱 (디바이스가 바뀌더라도 동일한 서비스를 제공) MVVM이라는 모델을 사용한다 
   *************************************************************************************************/
  @GetMapping("memberList")
  public String memberList(@RequestParam Map<String,Object> rmap, Model model){
    logger.info("memberList" + rmap);
    List<Map<String,Object>> mList = null;

    mList = memberLogic.memberList(rmap);
    logger.info("controller" + mList);
    model.addAttribute("mList", mList);
    return "forward:./memberList.jsp";
  }

  /************************************************************************************************** 
    제목 : 회원 정보 삭제 구현 
    작성자 : 이순신 
    작성일자 : 2024년 01월 02일
    @param : int mem_no - number(5)
    @return : int(1:삭제성공, 0이면 삭제 실패)
    참조 : 화면정의서를 참고하여 구현 
    개발패턴 : jsp - action(delete) - action(select) - jsp
    테이블 : 1-n관계에 있으면서 2개 이상의 테이블에 insert처리할 때? - 업무적인 depth가 깊다 
    SQL쿼리문 : 
    DELETE FROM member2023 WHERE mem_no = ?

    ************************************************************************************************/
    @GetMapping("memberDelete")
    public String memberDelete(int mem_no){
    logger.info("memberDelete 호출");
    int result = -1;
    String path = "";
    
    //insert here 
    result = memberLogic.memberDelete(mem_no);
    if(result == 1){
      path = "redirect:/memberList";
    }else{
      path = "redirect:/memberError.jsp";
    }
    return path;
  }/////////  end of memberInsert  ////////

    /************************************************************************************************** 
      제목 : 회원 정보 수정 구현
      작성자 : 이순신 
      작성일자 : 2024년 01월 03일
    @param : Map
    @return : int(1:입력성공, 0이면 입력 실패)
      참조 : 화면정의서를 참고하여 구현 
      개발패턴 : jsp - action(update) - action(update) - jsp
      테이블 : 1-n관계에 있으면서 2개 이상의 테이블에 insert처리할 때? - 업무적인 depth가 깊다 
      SQL쿼리문 : 
          UPDATE member2023
            SET mem_pw =:pw,
                mem_name =:name,
                zipcode =:zipcode,
                address =: address,
                mem_email =: email
            WHERE mem_no =:no;

    목업(Mock-up) or JUnit
    ************************************************************************************************/
    @PostMapping("memberUpdate")
    public String memberUpdate( @RequestParam Map<String,Object> pmap){
    logger.info("memberUpdate 호출");
    int result = -1;
    String path = "";
    
    //insert here 
    result = memberLogic.memberUpdate(pmap);
    if(result == 1){
      path = "redirect:/memberList";
    }else{
      path = "redirect:/memberError.jsp";
    }
    return path;
  }/////////  end of memberInsert  ////////
}
