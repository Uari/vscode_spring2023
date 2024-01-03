package com.example.aopdemo;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnyAdvice {
  Pattern p = Pattern.compile("m.*");

  //중복 코드를 분리하기 위해서 추가해야 할 코드를 분리 하였다 
  void invoke(Method m, Object obj, Object ...args) throws Exception{
    if(matches(m)){//메소드 파라미터로 넘어온 메소드 이름이 패턴에 일치하면
      //반복될 코드 조각 - con.setAutoCommit(false); 아직 커밋X
      System.out.println("====[[before]]");
    }
    m.invoke(obj, args);
    if(matches(m)){//메소드 파라미터로 넘어온 메소드 이름이 패턴에 일치하면
      System.out.println("====[[after]]");
    }
  }

  //AnyClass에 선언된 메소드 중에서 패턴에 일치 여부를 체크
  boolean matches(Method m){
    Matcher matcher = p.matcher(m.getName());
    //메소드 이름 앞에 m이면 true 아니면 false를 반환
    return matcher.matches();
  }
}
