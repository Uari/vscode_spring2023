package com.example.demo.di2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

public class MyContext {
  Map<String, Object> map = new HashMap<>();
  
  public MyContext(){
    map.put("testController", new TestController());
    map.put("testLogic", new TestLogic());
    map.put("testDao", new TestDao());
  }

  @SuppressWarnings("deprecation")
  public MyContext(Class<?> clazz){ //파라미터 자리 - myConfig - @Configuration - 스캔 - @Bean 등록
    try {
      Object myConfig = clazz.newInstance();
      for(Method m: clazz.getDeclaredMethods()){
        for(Annotation ann : m.getDeclaredAnnotations()){
          if(ann.annotationType() == Bean.class){
            //메소드 이름()을 키값으로 하여 값을 생성함
            map.put(m.getName(), m.invoke(myConfig, null));
          }
        }
      }
    } catch (Exception e) {
      
    }
  }
  Object getBean(String  id){
    return map.get(id);
  }
  //타입 비교 instanceof 
  //타입으로 객체 찾기 
  Object getBean(Class<?> clazz){
    for(Object obj:map.values()){
      if(clazz.isInstance(obj)){ //isInstance 가 instanceof
        return obj;
      }
    }
    return null;
  }
}
