## 스프링 게시판 프로젝트 
![화면 캡처 2024-09-25 174701](https://github.com/user-attachments/assets/ae0cb2be-1dd4-4648-9696-aaa64f95334c)

## 개발 기간 
총개발기간 2024.09.20 ~ 

## 프로젝트 소개 
자바,스프링 으로 게시판을 구현하는 프로젝트 입니다. 스프링에 대한 기본을 배울 수 있었습니다

## 개발 환경
- java11
- Srping 5.0.7 
- MySQL
- MyBatis
- Git,github
- AWS EC2

## 구현기능
- 로그인/로그아웃
- 게시글 생성/ 읽기/갱신삭제
- 게시판 메인 페이징 처리
- 댓글 생성/읽기/갱신/삭제

## ERD
![화면 캡처 2024-09-26 025136](https://github.com/user-attachments/assets/65afbbe3-4beb-49b1-b9fe-a609cf8c6631)


## 👀 서비스 화면

1.   메인페이지 - 게시글 검색기능과 , 페이징 처리

![화면 캡처 2024-09-25 174701.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/e475496f-9a1a-492c-a623-263531b978c8/d898d5a8-9110-4d06-8bb1-72ab9b5ecc67/%ED%99%94%EB%A9%B4_%EC%BA%A1%EC%B2%98_2024-09-25_174701.png)

---

1. 로그인 안했을떄 - 게시물 읽기만 가능

![화면 캡처 2024-09-26 105417.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/e475496f-9a1a-492c-a623-263531b978c8/72ac5fce-7e7e-4f80-887b-975a4b45ce64/%ED%99%94%EB%A9%B4_%EC%BA%A1%EC%B2%98_2024-09-26_105417.png)

1. 메인 페이지에서 글쓰기 작성 버튼을 누를시 로그인 체크 여부 확인하고 세션에 id 값이 없으면 로그인 화면으로 이동 

![화면 캡처 2024-09-26 104452.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/e475496f-9a1a-492c-a623-263531b978c8/41db2703-93dd-4b92-bd8a-4cb650ef864d/%ED%99%94%EB%A9%B4_%EC%BA%A1%EC%B2%98_2024-09-26_104452.png)

---

1.  해당 게시물과 작성자가 일치 할경우만  수정,삭제 버튼 생김

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/e475496f-9a1a-492c-a623-263531b978c8/4a2dd68b-d291-47ab-b83d-2c5224b81a4f/image.png)

1. 로그인을 하면 게시글을 쓸수 있음.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/e475496f-9a1a-492c-a623-263531b978c8/0fc09134-8488-45fc-b728-382ab8f072b9/image.png)

1. 검색바에 해당 게시글 내용을 검색했을 경

![화면 캡처 2024-09-26 110854.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/e475496f-9a1a-492c-a623-263531b978c8/9a920ca1-7e8a-4ffa-adf7-5e0a06f087c0/%ED%99%94%EB%A9%B4_%EC%BA%A1%EC%B2%98_2024-09-26_110854.png)



