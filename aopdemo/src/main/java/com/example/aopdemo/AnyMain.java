package com.example.aopdemo;

import java.lang.reflect.Method;

public class AnyMain {

public static void main(String[] args) throws Exception{
  Class<?> myClass = Class.forName("com.example.aopdemo.AnyClass");
    Object obj = myClass.newInstance();

    AnyAdvice anyAdvice = new AnyAdvice();

    for (Method m : myClass.getDeclaredMethods()) {
      anyAdvice.invoke(m, obj, null);
    }
}
}
