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

<img width="600" alt="스크린샷 2022-04-15 오후 1 42 21" src="https://user-images.githubusercontent.com/53418465/163519503-e6b5658b-3470-4176-b897-fbfbd9616246.png">

<h5>프로젝트 패키지 구성도</h5>

<img width="350" alt="스크린샷 2022-04-15 오후 2 19 18" src="https://user-images.githubusercontent.com/53418465/163524333-d6d9ed6d-48e3-4760-a113-ad62196d4547.png">


<h3>2.실행코드 및 화면</h3>

1.첫 등록 데이터베이스 작업 

로그인/로그아웃에 필요한 User 등록

IninDB.class

<img width="680" alt="스크린샷 2022-04-15 오후 1 52 24" src="https://user-images.githubusercontent.com/53418465/163520486-ac9b8b97-c49c-4dd1-bdc7-8ccfe1ee2c0f.png">

@PostConstruct
WAS가 올라가면서 bean이 생성될 때 딱 한 번 초기화함
bean lifecycle에서 오직 한 번만 수행된다는 것을 보장할 수 있다. 

메뉴 10개 등록 

data.sql

<img width="334" alt="스크린샷 2022-04-15 오후 1 55 45" src="https://user-images.githubusercontent.com/53418465/163520708-b8586f00-5415-4d6b-a80a-000e3969207b.png">

메뉴에 대한 데이터들도 InitDB 에서 insert 할려 했으나 sql을 통한 db insert 로 하는 방법도 같이 구현 해보고 싶었다.


2. security 

<img width="742" alt="스크린샷 2022-04-15 오후 2 23 19" src="https://user-images.githubusercontent.com/53418465/163524683-c3c7ed0d-b618-48d3-8743-94cacd28c594.png">

- 권한이 필요없는 url 들은 permitAll() 로 지정해 주었고 나머지느 로그인 인증을 통해 접속이 가능하게 하였다.
- exceptionHandling().authenticationEntryPoint(ajaxAwareAuthenticationEntryPoint("/login")) 부분은 ajax 통신을 통한 웹형식으로 되어있어 인증이 거치지 않은\n
유저는 403 status 를 보내 로그인 페이지로 이동 시키는 필터기능을 하고있다.

<img width="809" alt="스크린샷 2022-04-15 오후 2 29 59" src="https://user-images.githubusercontent.com/53418465/163525318-71dac1ac-d976-42ae-835b-d755408d29c6.png">
<img width="851" alt="스크린샷 2022-04-15 오후 2 31 00" src="https://user-images.githubusercontent.com/53418465/163525431-fb1c2c27-8b9b-43d3-9571-984c7f901505.png">

    
    


