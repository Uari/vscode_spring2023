package com.example.demo.di;
//추상클래스
//추상클래스는 추상메소드와 일반메소드를 모두 가짐
//생성자도 가짐
//변수선언 가능
//인터페이스는 일반메소드는 못 가짐
//추상클래스와 인터페이스의 공통점 = 둘 다 반드시 구현체 클래스가 있어야 함
//추상클래스의 구현체일 땐 extends 사용
//인터페이스의 구현체일 땐 implements 사용
public abstract class Duck {
	FlyBehavior flybeavior = null;
	QuackBehavior quackbehavior = null;
	public Duck() {}
		public abstract void display();
		public void performFly() {
			flybeavior.fly();
		}
		public void performFlyQuack() {
			quackbehavior.quack();
		}
		public void swimming() {
			System.out.println("모든 오리는 물 위에 뜬다.");
		}
}
