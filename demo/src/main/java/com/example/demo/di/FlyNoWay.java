package com.example.demo.di;
//메소드 선언만 되어 있으므로 오리클래스가 되기 위한 명세서의 역할
public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("나는 날지 않아!");
	}

}
