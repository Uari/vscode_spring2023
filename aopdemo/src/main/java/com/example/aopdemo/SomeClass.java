package com.example.aopdemo;


public class SomeClass {
  public void methodA(){
    System.out.println("====[[before]]");
    System.out.println("methidA처리구현");
    System.out.println("====[[after]]");
  }
  
  public void methodB(){
    System.out.println("====[[before]]");
    System.out.println("methidB처리구현");
    System.out.println("====[[after]]");
  }
  
  public void methodC(){
    System.out.println("====[[before]]");
    System.out.println("methidC처리구현");
    System.out.println("====[[after]]");
  }
}
/* 
 * 여러 메소드에 공통코드를 추가해야 한다면??
 * 똑같은 코드가 세번이나 중복되고 있다.
 * 메소드가 많아지면 반복되는 코드도 더 늘어난다.
 * 
 * 중간코드 지점은 약속이 불가능하다
 * 공통코드가 추가될 수 있는 지점은 앞에 혹은 뒤에만 가능하다.
 * 
 * AOP관련 용어 
 * target : advice(부가기능)가 추가될 객체 - AnyClass.java
 * 
 * Class<?> myClass = Class.forName("com.example.demo.aopdemo.AnyClass");
 * Object obj = myClass.newInstance();
 * 
 * advice : target클래스에 동적으로 추가될 부가기능(코드) - 부가기능의 반대말은 핵심기능 - AnyAdvice.java
 *  : 패턴 매칭 비교 메소드 추가
 *  : 중복코드를 분리하기 위해서 추가해야 할 코드를 분리하는 작업
 * joinpoint : advice가 추가(join)될 대상이다(메소드)
 * pointcut : joinpoint들을 정의한 패턴임 - 정규식 Ex)execution(* com.example.demo.*Logic.cud*(..)) -> Logic 클래스에 한에서 모든 메소드에 pointcut 하겠다
 * '(..)' : 매개변수가 하나이든 , 둘이든, 아니면 없어도 OK 
 * //cudEmp, cudBoard, cudNotice (대상 O)   /  getBoardList, getNoticeList (대상 X)
 * pointcut expression
 * 실전예) @Around("execution(* com.example.*.*(..))")
 * 
 * 
 * proxy - 스프링에서 aop자동으로 설정하기 위해 필요하다 
 * 
 * weaving(꿰매기) : taget에 advice를 추가해서 proxy 생성해줌 
 * 
 * 
 * 
 * Advice의 종류
 * 
 * 설정하기 - xml , 어노테이션
 * around advice - @Around - 메소드의 시작부분과 끝부분 모두를 감싸줌
 * before advice - @Before - 메소드 시작부분
 * after  advice - @After  - 메소드 끝부분
 * 
 * try{
 *  m.invoke(); //메소드 호출
 *  @AfterReturning
 * }catch(){
 *  @Afterthrowing
 * }
 */