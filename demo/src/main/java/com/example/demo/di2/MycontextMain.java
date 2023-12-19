package com.example.demo.di2;
//실습 제목 - ApplicationCentext
//소개 목적 - myBatis같은 외부 라이브러리를 사용할 때 사용하라 - 공통코드작서하기 - 공통팀에 근무 
//EX) DatabaseConfiguration 
public class MycontextMain {
  
  public static void main(String[] args) {
    MyContext mc = new MyContext(MyConfig.class);
    System.out.println(mc.map);

    TestController testController2 = new TestController();
    System.out.println("testController2 : "+testController2);
    
    TestLogic testLogic = (TestLogic)mc.getBean("testLogic"); // - IoC, 관리받음 byName
    TestDao testDao = (TestDao)mc.getBean(TestDao.class); 
    TestController testController = (TestController)mc.getBean("testController"); // - IoC, 관리받음 byName
    
    testLogic.setTestDao(testDao);
    System.out.println("testController : "+testController);

    testController.setTestLogic(testLogic);//관계있는 클래스에 객체 주입코드 -> 생략가능 : @Autowired 사용
    testController.testList(); 
    //같은 주소 번지를 출력하고 있구나!! - 싱글톤 패턴 디폴트 
    //멀티 스레드를 운영하여 한정된 자원을 여러 사용자가 누릴 수 있다.
    TestController testController3 = (TestController)mc.getBean(TestController.class);// - IOC 관리받음 byClass
    System.out.println("testController3 : "+testController3);
  }
}
