package com.example.aopdemo;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

//@SpringBootApplication
public class LogApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(Config.class);

		AnyTarget at = ac.getBean("anyTarget", AnyTarget.class);
		// AnyTarget at2 = (AnyTarget)ac.getBean("anyTarget");
		at.cudMethodA(1, 2);
		at.cudMethodB(1, 2, 3);
		System.out.println(at.otherMethodC(4, 1));

	}
}
	@EnableAspectJAutoProxy//AOP 자동설정
	@ComponentScan  //사용자 정의 빈을 스캔해서 등록해줌 - 
	@Configuration
	class Config{
		//완전 빈 깡통 무슨 역할? - 매개변수에 필요한 클래스 타입이다.
	}

	@Component
	@Aspect
	class LoggingAdvice {
		@Around("execution(* com.example.aopdemo.*Target.cud*(..))")
		public Object logMethod(ProceedingJoinPoint pj) throws Throwable{
			//시작 되는 공통코드 - 부가기능임 - 핵심기능은 AnyTarget의 메소드들이다
			long start = System.currentTimeMillis();
			System.out.println("{{start}}" + pj.getSignature().getName()
																			+ Arrays.toString(pj.getArgs()));
			Object result = pj.proceed(); //AnyTarget 클래스의 메소드 호출
			//끝부분에 추가될 공통코드 - AnyTarget의 끝부분에 추가될 코드
			System.out.println("account : " + result);
			System.out.println("{{end}}" + (System.currentTimeMillis() - start) + "ms");
			return result; //반환값은 메소드 호출 결과를 반환함
		}
	}

	@Component //빈등록을 위해 추가함 
	class AnyTarget{ //Taget의 역할 클래스 - 여기에 Advice가 적용
		int cudMethodA(int x, int y){
			int account = x+y;
			return account;
		}
		int cudMethodB(int x, int y, int z){
			int account = x*y*z;
			return account;
		}
		int otherMethodC(int x, int y){
			int account = x-y;
			return account;
		}
	}
	@Component //빈등록을 위해 추가함 
	class OtherTarget{ 
	
	}

