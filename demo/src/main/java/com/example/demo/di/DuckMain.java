package com.example.demo.di;

import java.io.FileReader;
import java.util.Properties;

public class DuckMain {
  static Duck getDuck(int i){ //객체를 제공하는 쪽
    //메소드로 객체를 제공하는 쪽이 변경할 코드가 적다 
    return new RubberDuck();
  }

  static Duck getDuck(double d){ //객체를 제공하는 쪽
    return new WoodDuck();
  }

  @SuppressWarnings("deprecation")
  static Object getObject(String key) throws Exception{
    Properties p = new Properties();
    //duckInfo.txt를 읽어서 Properties 저장함 
    p.load(new FileReader("duckInfo.txt")); 
    //Class는 클래스 설계도를 얻어내는 클래스 
    Class<?> clazz = Class.forName(p.getProperty(key)); //rubber, wood
    return clazz.newInstance();
  }

  public static void main(String[] args) {
    //고무오리 타입이 호출되도록
    Duck duck = DuckMain.getDuck(1);
    if(duck instanceof RubberDuck){
      System.out.println("RubberDuck"); 
    }
    Duck duck2 = DuckMain.getDuck(1.0);
    if(duck2 instanceof WoodDuck){
      System.out.println("WoodDuck"); 
    }
  }
}
/* 
 * 선언부 생성부 
 * 객체를 사용하려는 쪽
 * 개발자가 수정해야할 코드가 많아짐 - 문제제기
 * :왜냐면 선언부와 생성부 모두를 수정해야 함 - 생성자 오버로딩 - 전변 - 고유명사 - 모두수정해야함
 * :여기다가 이 제어권을 외부에서 갖자 - 스프링 컨테이너 - IOC
 * 기존방식의 문제점
 * 컴포넌트간의 결함도가 높아서 컴포넌트 확장 및 재사용이 어려운 문제 발생함
 * 
 * IOC사용시
 * : 제어권 컨테이너에 넘어가  객체의 생명주기를 컨테이너 전담하게 됨 - 뼤앗김, 자유억압 -> 나이스 
 * ApplicationContext(싱글톤 미리 로딩 ), BeanFactory(게으른 - getBean 호출 될 때까지 기다림 )
 * 
 * MallardDuck myDuck = new MallardDuck();
 * WoodDuck himDuck = new WoodDuck();
 * 
 * 
 * 결함도 , DI , 인터페이스 , 추상메소드, IOC, reflection, 
 * ApplicationContext, BeanFactory
 * 
 * 
 */
