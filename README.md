## 스프링 게시판 프로젝트 
총개발기간 2024.09.20 ~ 2024~10.01

배포 URL: http://54.180.101.172:8080/web  <br>
아이디: asdf  <br>
비밀번호: 1234 


## 프로젝트 소개 
스프링을 학습하고 공부 한걸 토대로 웹의 가장 기초가 되는 게시물 프로젝트를 연습해 보고 싶어서 해당 주제를 선정하게 되었습니다.


## 개발 환경
- java 11
- Srping 5.0.7 
- MySQL 8.0.27
- MyBatis
- Git,github
- AWS EC2

## 구현기능
- 로그인/로그아웃
- 게시글 생성/ 읽기/갱신삭제
- 게시판 메인 페이징 처리
- 댓글 생성/읽기/갱신/삭제
- 대댓글 

## ERD
![화면 캡처 2024-09-26 025136](https://github.com/user-attachments/assets/65afbbe3-4beb-49b1-b9fe-a609cf8c6631)


## 프로젝트 구조

```
─src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───myportfolio
│   │   │           └───web
│   │   │               ├───controller
│   │   │               ├───dao
│   │   │               ├───domain
│   │   │               └───service
│   │   ├───resources
│   │   │   ├───css
│   │   │   ├───mapper
│   │   │   └───META-INF
│   │   └───webapp
│   │       ├───css
│   │       ├───resources
│   │       │   └───css
│   │       └───WEB-INF
│   │           ├───classes
│   │           ├───spring
│   │           │   └───appServlet
│   │           └───views

```
