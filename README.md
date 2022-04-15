# 티오더 과제

<h3>1. 설계도 </h3>

- front = html, js, jquery, bootstrap
- back = jpa, spring boot
- DB = H2-Database

<h5>Database 테이블</h5>

- 회원 (Account)
- 메뉴 (Menu)
- 장바구니 (Cart, CartItem)
- 주문 (Order)
- 결제 (Payment)

<h5>Database 테이블 다이어그램</h5>

<img width="650" alt="스크린샷 2022-04-15 오후 1 42 21" src="https://user-images.githubusercontent.com/53418465/163519503-e6b5658b-3470-4176-b897-fbfbd9616246.png">

<h5>프로젝트 패키지 구성도</h5>

<img width="312" alt="스크린샷 2022-04-15 오후 2 33 17" src="https://user-images.githubusercontent.com/53418465/163525630-7936015f-4f29-4ed6-a616-a548d7ebf606.png">


<h5>서비스 흐름</h5>

- 로그인 -> 장바구니 담기 -> 주문 -> 주문한 장바구니 목록 삭제 -> 결제 -> 주문한 목록들 삭제



<h3>2.실행코드</h3>

1.entity 클래스

- account 

  <img width="400" alt="스크린샷 2022-04-15 오후 2 39 02" src="https://user-images.githubusercontent.com/53418465/163526286-b311fc91-5c81-431a-8ee7-8a48f0426069.png">

- menu
  
  <img width="546" alt="스크린샷 2022-04-15 오후 2 39 53" src="https://user-images.githubusercontent.com/53418465/163526482-facbda50-40c0-4419-ae61-ec5303f37bf0.png">

- cart

  <img width="400" alt="스크린샷 2022-04-15 오후 2 39 20" src="https://user-images.githubusercontent.com/53418465/163526316-77eac044-5b83-495c-bfce-a47a0d8e19f9.png">

- cartItem
  
  <img width="400" alt="스크린샷 2022-04-15 오후 2 39 34" src="https://user-images.githubusercontent.com/53418465/163526381-71a00978-533d-444e-9bf1-bd61fcec768a.png">
  
- order

  <img width="459" alt="스크린샷 2022-04-15 오후 2 40 17" src="https://user-images.githubusercontent.com/53418465/163526507-0ad8ba68-90ca-4033-af00-de978e7be777.png">

- payment
  
  <img width="492" alt="스크린샷 2022-04-15 오후 2 40 34" src="https://user-images.githubusercontent.com/53418465/163526616-84651531-9dc4-45c7-8536-840f87975a0e.png">
  

2.첫 등록 데이터베이스 작업 

로그인/로그아웃에 필요한 User 등록

IninDB.class

<img width="650" alt="스크린샷 2022-04-15 오후 1 52 24" src="https://user-images.githubusercontent.com/53418465/163520486-ac9b8b97-c49c-4dd1-bdc7-8ccfe1ee2c0f.png">

@PostConstruct
WAS가 올라가면서 bean이 생성될 때 딱 한 번 초기화함
bean lifecycle에서 오직 한 번만 수행된다는 것을 보장할 수 있다. 

메뉴 10개 등록 

data.sql

<img width="334" alt="스크린샷 2022-04-15 오후 1 55 45" src="https://user-images.githubusercontent.com/53418465/163520708-b8586f00-5415-4d6b-a80a-000e3969207b.png">

메뉴에 대한 데이터들도 InitDB 에서 insert 할려 했으나 sql을 통한 db insert 로 하는 방법도 같이 구현 해보고 싶었다.


3. security 

<img width="650" alt="스크린샷 2022-04-15 오후 2 23 19" src="https://user-images.githubusercontent.com/53418465/163524683-c3c7ed0d-b618-48d3-8743-94cacd28c594.png">

- 권한이 필요없는 url 들은 permitAll() 로 지정해 주었고 나머지느 로그인 인증을 통해 접속이 가능하게 하였다.
- exceptionHandling().authenticationEntryPoint(ajaxAwareAuthenticationEntryPoint("/login")) 부분은 ajax 통신을 통한 웹형식으로 되어있어 인증이 거치지 않은
유저는 403 status 를 보내 로그인 페이지로 이동 시키는 필터기능을 하고있다.
<img width="650" alt="스크린샷 2022-04-15 오후 2 31 46" src="https://user-images.githubusercontent.com/53418465/163525530-59c13487-3b6e-43c4-be72-541917ce6a6b.png">
<img width="650" alt="스크린샷 2022-04-15 오후 2 31 00" src="https://user-images.githubusercontent.com/53418465/163525431-fb1c2c27-8b9b-43d3-9571-984c7f901505.png">


4. cart service code 

- 장바구니 담기 code 

  <img width="741" alt="스크린샷 2022-04-15 오후 2 48 19" src="https://user-images.githubusercontent.com/53418465/163526954-11290103-6d65-41a0-a92f-c7ff9b9d4299.png">

- 장바구니 삭제 code 

  <img width="592" alt="스크린샷 2022-04-15 오후 2 49 20" src="https://user-images.githubusercontent.com/53418465/163527035-164d899f-e326-41b6-9111-16e6c26a3407.png">

5. order service code 

- 주문 완료 code 

  <img width="596" alt="스크린샷 2022-04-15 오후 2 50 51" src="https://user-images.githubusercontent.com/53418465/163527154-37bce9c3-0c12-4d1a-90a8-048710f391da.png">

- 주문 삭제 code

  <img width="423" alt="스크린샷 2022-04-15 오후 2 52 07" src="https://user-images.githubusercontent.com/53418465/163527259-c3d63887-3edd-4de5-af7a-9856452856f4.png">

6. payment service code 

- 결제 완료 code

  <img width="603" alt="스크린샷 2022-04-15 오후 2 53 09" src="https://user-images.githubusercontent.com/53418465/163527362-3df4483f-794d-435b-bf51-8b44dd8b6423.png">
  

<h3>3.실행 동영상</h3>

https://youtu.be/mm-T6Y7YX6o


