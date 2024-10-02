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
![화면 캡처 2024-09-26 025136](https://github.com/user-attachments/assets/65afbbe3-4beb-49b1-b9fe-a609cf8c6631) <BR>




## CLOC 결과
```
-----------------------------------------------------------------------------------
Language                         files          blank        comment           code
-----------------------------------------------------------------------------------
Java                                24            398            120           1417
XML                                 16             55             20           1003
JSP                                  5            133             36            807
Maven                                1             17             20            183
CSS                                  1              6              0             38
Visualforce Component                1              0              0             11
-----------------------------------------------------------------------------------
SUM:                                48            609            196           3459
-----------------------------------------------------------------------------------

```



## 프로젝트 구조

```
─src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───myportfolio
│   │   │           └───web
│   │   │               ├───controller
│   │   │               │       BoardController.java
│   │   │               │       CommentController.java
│   │   │               │       LoginController.java
│   │   │               │       SimpleRestController.java
│   │   │               │
│   │   │               ├───dao
│   │   │               │       BoardDao.java
│   │   │               │       BoardDaoImpl.java
│   │   │               │       CommentDao.java
│   │   │               │       CommentDaoImpl.java
│   │   │               │       UserDao.java
│   │   │               │       UserDaoImpl.java
│   │   │               │
│   │   │               ├───domain
│   │   │               │       BoardDto.java
│   │   │               │       CommentDto.java
│   │   │               │       PageHandler.java
│   │   │               │       SearchCondition.java
│   │   │               │       User.java
│   │   │               │
│   │   │               └───service
│   │   │                       BoardService.java
│   │   │                       BoardServiceImpl.java
│   │   │                       CommentService.java
│   │   │                       CommentServiceImpl.java
│   │   │
│   │   ├───resources
│   │   │   │   log4j.xml
│   │   │   │   mybatis-config.xml
│   │   │   │
│   │   │   ├───css
│   │   │   │       menu.css
│   │   │   │
│   │   │   ├───mapper
│   │   │   │       boardMapper.xml
│   │   │   │       commentMapper.xml
│   │   │   │
│   │   │   └───META-INF
│   │   └───webapp
│   │       ├───css
│   │       │       menu.css
│   │       │
│   │       ├───resources
│   │       │   └───css
│   │       │           menu.css
│   │       │
│   │       └───WEB-INF
│   │           │   web.xml
│   │           │
│   │           ├───classes
│   │           ├───spring
│   │           │   │   root-context.xml
│   │           │   │
│   │           │   └───appServlet
│   │           │           servlet-context.xml
│   │           │
│   │           └───views
│   │                   board.jsp
│   │                   boardList.jsp
│   │                   index.jsp
│   │                   loginForm.jsp
│   │                   test.jsp

```
