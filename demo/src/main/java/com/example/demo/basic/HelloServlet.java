package com.example.demo.basic;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
//tomcat10.x버전에서는 4.0서블릿 사용불가함 - 실습 안됨 - 현재 6.0
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/runTime")
public class HelloServlet extends HttpServlet{
  Logger logger = LoggerFactory.getLogger(HelloServlet.class);

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    logger.info("service");

    //1.전처리 작업
    //현재 작업시간
    long startTime = System.currentTimeMillis();
    //2. 작업처리
    for(double d=0; d<99999999.0; d++){
      //지연상태 
    }
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.print("<html>");
    out.print("<head><title>서블릿테스트</title></head>");
    out.print("<body>내용 ... </body>");
    out.print("</html>");
    //3. 후처리작업 - 수행시간 계산
    //종료시간 - 시작시간 = 소요시간
    System.out.print("["+ req.getRequestURI() +"]");
    System.out.println(" time = "+ (System.currentTimeMillis() - startTime) + "ms");
  }
}
