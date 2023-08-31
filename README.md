# :mortar_board:학습장, 전국 평생학습 강좌 찾기 서비스:mortar_board:

## 1. 프로젝트 개요
> 멋쟁이 사자처럼의 11기 전체 해커톤 출품작으로 전국의 평생학습 강좌를 찾아주는 웹사이트를 기획하게 되었다. 해당 해커톤의 출품 기준은 "사회 각 분야에서 발생하는 디지털 격차 해소에 도움을 줄 수 있는 서비스 제작"이었다. 많은 고민을 하던 중 공공서비스 분야가 디지털화 되어가면서 사회적 취약 계층에 위치한 사람들이 정보 접근에 많은 어려움을 겪고 있는 것을 확인하였다. 이에 따라 공공데이터를 이용하여 상대적으로 접근하기 어려운 정보의 접근성을 높여서 모든 사람이 해당 정보 취득에 있어 용이하게끔 하고자 하였다.
## 👩🏻‍💻 Server
| [오동재](https://github.com/djdongjae) |
|:---:|
| <img src="https://github.com/djdongjae.png" width="150"> |
## 👩🏻‍💻 Client
| [김은혜](https://github.com/gracekim527) | [백하윤](https://github.com/hayoon07) | [박지균](https://github.com/jivirus) |
|:---:|:---:|:---:|
| <img src="https://github.com/gracekim527.png" width="150"> | <img src="https://github.com/hayoon07.png" width="150"> | <img src="https://github.com/jivirus.png" width="150"> |

## 2. 평생학습의 불평등성
> 활용할 수 있는 공공데이터, 즉 프로젝트의 주제가 되는 데이터를 탐색하던 중 평생학습이라는 분야를 발견하게 되었다. 평생학습의 사전적 정의는 "개인의 자아실현을 위하여 언제 어디서나 원하는 교육을 받아 삶의 질을 향상시킬 수 있는 교육을 총칭하는 것" 이다. 하지만 아이러니하게도 사전적 정의와 반하게, 한국교육개발원의 2022년도 통계자료에서는 소득이 높을 수록, 나이가 젊을 수록, 지역이 수도권 지역일 수록 참여율이 높았던 반면 사회적 취약계층의 참여율은 저조한 것을 확인할 수 있었다. 이는 평생학습의 수강신청과 강좌 수강 자체가 온라인화 되어가면서 정보 접근에 제약이 발생한 것으로도 해석할 수 있었다.
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/373df7be-040e-4038-869d-fcf11667ba00">

## 3. 전국평생학습강좌 공공데이터
> 해당 프로젝트를 진행하기 위해서 공공데이터포털의 "전국평생학습강좌표준데이터"를 활용하였다. 해당 데이터는 전국의 모든 평생학습 강좌를 일괄적으로 모아 놓은 데이터로 강좌명, 강좌 내용, 강사명, 교육 기관 등 다양한 데이터를 제공한다. 또한 제공하는 데이터 형식 역시 XLS, JSON, XML, CSV 형식의 파일데이터 뿐만 아니라 OpenAPI를 통해 실시간 데이터까지 제공한다. 이를 기반으로 프로젝트의 ERD를 설계하고 데이터베이스에 해당 데이터를 파싱하여 저장하였다. 간단한 원리를 설명하자면 현재까지의 데이터는 JSON 포맷의 파일 데이터를 활용하여 서버 실행과 동시에 저장을 진행하였고, 추후 매일 업데이트 되는 강좌 정보는 OpenAPI를 활용하여 스케줄링을 통해 매일 00시 00분에 자동으로 데이터를 불러오도록 하였다. 또한 접수 기간이나 교육 기간이 종료된 날짜는 저장하지 않거나 삭제하였으며 스케줄링을 통해 매일 D-Day를 계산하여 업데이트 하였다.
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/67f42a21-968b-424d-963f-5937f49e112b">
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/16ed59f6-8116-4ba2-be5f-9ff36395a2f0">
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/bdb9fabd-2789-43fd-a6ac-3176b255434d">

## 4. 프로젝트 구조
> 해당 프로젝트는 강좌 검색 및 필터링을 통한 조회, 소셜 로그인을 통한 강좌 찜하기 및 후기 작성의 기능을 주로 구현하였다. 따라서 Lecture(강좌), User, Wish(찜), Review(후기) 총 4개의 테이블을 활용하였으며 데이터베이스에는 최소한의 정보만 저장하고 유추 가능한 정보는 최대한 로직을 통해 구현하고자 하였다. 페이지의 경우 랜딩 페이지인 index.html, 강좌 리스트 페이지인 list.html, 강좌 상세 정보인 detail.html, 강좌 후기 작성 페이지인 create.html 및 edit.html 그리고 찜한 강좌, 작성한 후기 등의 유저 정보를 열람할 수 있는 mypage.html로 구성하였다.
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/298b4a11-2db3-437b-a714-ef4cbe646a78">

## 5. 강좌 검색 및 필터링을 통한 조회 기능
> 강좌 검색의 경우 해당 강좌명을 기준으로 검색을 할 수 있도록 구현하였다. 이는 JPA query creation(쿼리 메소드 자동 구현) 기능을 활용하여 간단하게 구현하였다. 검색창을 통해 Request Parameter를 전달 받으면 이를 활용한 api를 통해 해당 검색 내용을 포함하는 강좌명을 가진 강좌들을 리턴하도록 하였다. 필터링 기능의 경우 query creation을 활용하기에는 로직이 너무 복잡해지기에 간단하게 Stream filter를 통해 해당되는 필터에 맞는 강좌를 걸러내어 리턴하는 방식을 활용하였다. 또한 전체 강좌의 수가 6000개에 육박했기 때문에 pagination 기능을 활용하여 한 페이지에 9개의 강좌만 조회되도록 구현하였다.
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/e21c7aea-0b6f-406d-9b7d-4f6af4ca343c">
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/95360bf1-8f78-4ca7-9b5b-ceaab2062611">
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/6c2cddfc-cdd2-4533-9582-201b990c4674">

## 6. 찜하기 기능 및 후기 작성 기능
> 로그인한 유저를 위한 기능으로써 해당 강좌를 찜하고 후기를 작성할 수 있는 기능을 구현하였다. 해당 기능 모두 강좌 테이블과 유저 테이블의 기본키를 외래키로 받아서 테이블을 구성하고 찜과 후기를 생성 또는 삭제할 때 레코드가 생성되거나 삭제되는 형식으로 구성하였다. 작성한 후기의 경우 마이페이지 또는 상세 정보 페이지에서 열람할 수 있도록 구성하였다.
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/79047609-5741-4e6e-a2dc-de351742ce26">
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/58996230-43e3-4f09-8774-4a0d06ba7ea3">
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/2c5945e3-4bce-452f-a12c-2402252f7196">

## 7. 소셜 로그인
> Spring Security와 OAuth 2.0을 활용하여 구글 로그인과 네이버 로그인을 연동하여 로그인 기능을 구현하였다. 유저에 대한 많은 정보가 필요하지는 않기 때문에 자체 로그인 기능은 구현하지 않았다. 해당 서비스 사이트에서 client 인증 정보를 발급 받고 유저의 정보를 받아왔으며 세션 저장 기능을 활용하여 권한 및 인증이 필요한 곳에 이를 적용하였다.
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/0e188551-65d6-406c-859a-a3ff4c614be2">
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/ac917e3f-d39f-4572-999a-e4acd0c12df5">

## 8. 활용 스택
> 해당 프로젝트는 기본적으로 스프링 부트 2.7.14 버전과 Thymeleaf를 활용하여 제작되었다. 웹서버 프로그래밍이다 보니 프론트와 백엔드 구분이 명확하지 않고 와이어 프레임 형식으로 디자인이 적용 되지 않은 뷰를 통해 백엔드 기능을 테스트하고 추후 필요한 디자인을 입히는 방식으로 프로젝트를 진행하였다. 전체 활용 스택은 다음과 같다.
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/f4578ea3-3ca0-49b6-bbe2-0f350943536c">
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/5a2493d4-78ea-4bdf-bc67-b88222ab7753">

## 9. 기타 구현 모습
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/a43ffe47-2495-4842-a9f8-8c120015d7f2">
<img src="https://github.com/LikeLion-11th-SKHU/hakSeubJang-BE/assets/93889207/308e93dd-11a5-45c8-a4ac-0dd38e0d0bd6">

