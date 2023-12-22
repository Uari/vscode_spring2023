package com.example.demo.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.vo.OAuthToken;

@Controller
@RequestMapping("/auth/*")
public class kakaoController2 {
  Logger logger = LoggerFactory.getLogger(kakaoController2.class);

  // @ResponsBody를 붙이면 Data를 리턴해준느 컨트롤러 메소드가 된다
  @GetMapping("/kakao/callback")
  public @ResponseBody String kakaoCallback(String code) {
    logger.info("kakaoCallback");
    // HttpHeaders : 해더값에 클라이언트의 요청이나 서버의 응답에 포함되어 부가적인 정보를 담고 있음
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    // MultiValueMap : 기존 map 형식에서 list특성이 있다
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", "818b11bd2a07b66b6f5537b683402953");
    params.add("redirect_id", "http://localhost:8000/auth/kakao/callback");
    params.add("code", code);

    // HttpEntity : 해더 정보를 읽는다 ( 토큰을 가져온다 )
    HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params, headers);
    // RestTemplate : Spring에서 지원하는 객체로 간편하게 Rest 방식 API를 호출할 수 있는 Spring 내장 클래스
    RestTemplate rt = new RestTemplate();
    // Http 요청하기 - POST방식으로 그리고 response 변수의 응답 받음
    // https://kauth.kakao.com/oauth/token : 토큰 발급 요청 주소
    ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, tokenRequest,
        String.class);

    Gson g = new Gson();
    OAuthToken oat = g.fromJson(response.getBody(), OAuthToken.class);

    // 사용자 정보 가져오기
    HttpHeaders headers2 = new HttpHeaders();
    //Authorization : 클라이언트가 적절한 자격 증명을 가지고 있는지를 확인하기 위한 정보를 담음
    headers2.add("Authorization", "Bearer " + oat.getAccess_token());
    headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

    HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(headers2);
    RestTemplate rt2 = new RestTemplate();
    ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
        profileRequest,
        String.class);

    return response2.getBody();

    // return oat.getAccess_token();
  }
}