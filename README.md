## OAuth2.0 클라이언트와 스프링 시큐리티를 사용하여 JWT 기반 로그인 구현

### 기술스택
- 백엔드
  - Spring Boot 3.4.0
  - Security 6.4.1
  - JJWT 0.12.3
- 프론트엔드
  - React 19.0
  - Javascript
  - Html
 
### 목표
  - JWT 기반의 인증/인가를 구현(일반 로그인), 신뢰할 수 있는 외부 사이트(구글, 네이버)로 부터 인증을 받고 전달 받은 유저 데이터를 활용하여 JWT를 발급하고 인가를 진행
  - 인증 받은 데이터는 MySQL 데이터베이스를 활용하여 저장하고 관리
  - 보안을 위한 refreh token 구현
 
### 구현
- 일반 로그인, 네이버/구글 소셜 로그인 (코드 방식) 후 JWT 발급
   - 일반 로그인 : 백엔드에서 응답 헤더에 access token, 쿠키에 refresh token 넣어 전송 -> 프론트엔드가 응답 받은 헤더의 토큰을 local storage에 저장
   - oauth2 로그인 : 백엔드에서 로그인 처리 후 쿠키에 토큰(access, refresh) 넣어서 리다이렉트 -> 프론트에서 다시 백엔드로 요청하여 백엔드 응답 헤더에 토큰(access) 넣어 전송 -> 프론트엔드가 응답 받은 헤더의 토큰을 local storage에 저장 (쿠키에 access token을 null 처리)
- 로그인 :  access token, name은 local Storage에 저장, refresh token은 DB, Cookie에 저장
- 로그아웃 : refresh token을 검증하고, DB에서 삭제. local storage, 쿠키 삭제
- 재발급 : access token 만료, 쿠키에 refresh token 존재 할때 백엔드에서 401 응답 -> 프론트엔드 재발급 요청 -> 쿠키에 refrehs token 검증하여 access token 재발급

참고 자료 https://www.devyummi.com/page?id=66ae0648cceeb3578956bce2
