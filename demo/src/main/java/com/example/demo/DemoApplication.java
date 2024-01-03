package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication // = @Configuration + @EnableAutoConfiguration + @ComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//ApplicationContext ac = SpringApplication.run(DemoApplication.class, args);
		// 등록된 빈의 목록 - 미리 로딩되어 있는 객체 목록을 확인해 보는 코드임 - 로그확인시 불편하여 주석처리함
		//String[] beanNames = ac.getBeanDefinitionNames(); //스프링에 등록된 클래스 빈 이름을 배열에 담음
		//Arrays.sort(beanNames); // 정렬하기
		// 배열 스트림으로 변환하여 목록 출력
		//Arrays : 배열에 있는 정보를 그대로 출력할 수 없어 stream으로  변환 시킴 
		//Arrays.stream(beanNames).forEach(System.out::println);// 빈의 목록 
		//Arrays.stream(beanNames) //스트림 변환
		//.forEach(System.out::println); //빈 목록 출력 - 이른 인스턴스화 확인 코드 
	}
}
