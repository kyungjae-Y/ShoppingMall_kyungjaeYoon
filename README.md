메인 메뉴 - 실행시 초기값 : MallMain
==============================
> 1. 회원가입 - MallJoin

	- id 중복검사 (1001부터) 비밀번호만 정규화 해보기 닉네임 중복 허용
	1000/admin/admin/관리자
	- 멤버에 처음은 항상 이걸로 고정 - 메인 실행시 자동 가입
> 2. 로그인 - MallLogin


	  - 관리자 로그인, 멤버 로그인메뉴 따로 있음, id 값 controller 가 저장
> 0. 종료 - ""

관리자 메뉴 - AdminMain
==============================
> 1. 회원관리 - AdminMember

	[1] 회원 목록	AdminMemberList
	[2] 회원 삭제 	AdminMemberDelete
	[3] 뒤로가기	AdminMain
	[0] 종료	""

	- 전체 회원 목록 num, id, pw, name
	- 회원 삭제 시 구매목록 삭제 안내문구, 게시글을 남겨둔다 
	- admin 관리자는 삭제 불가능
> 2. 상품관리 - AdminItem

	[1] 아이템 추가	AdminItemAdd		
	[2] 아이템 삭제	AdminItemDelete
	[3] 총 매출 	AdminRevenue
	[4] 뒤로 가기	AdminMain
	[0] 종료	""
 
	- 가격 표시
	- 아이템 삭제 시 구매목록도 삭제
> 3. 게시판관리 - AdminBoard

	[1] 게시글 목록	AdminBoardList
	[2] 게시글 삭제	AdminBoardDelete
	[3] 뒤로 가기	AdminMain
	[0] 종료 	""

	- 1번 선택 시
	[1] 이전 	AdminBoardBeforePage
	[2] 이후 	AdminBoardAfterPage
	[3] 게시글보기 	AdminBoardViewPage
	[4] 뒤로가기	AdminBoard
	[0] 종료 	""
	- 관리자는 전체 게시글 삭제 권한 있음
> 4. 로그 아웃

	- MallMain 으로 이동
	- 로그아웃 시 ID값 ""로 변경
> 5. 파일 저장

	- 각각 DAO 에서 data String 파일 만들어서 AdminFileSave에 넘겨주고 txt 파일 만들기
	       Board.txt 	cart.txt 	item.txt	member.txt
> 0. 종료 - ""

사용자 메뉴 - MemberMain
==============================
> 1. 상품구매 - MemberShopping

	- 상품이 있으면 카운트만 추가하고 없으면 새로 카트리스트에 등록 (1개씩)
	- 카테고리 선택 후 아이템 이름과 가격을 같이 띄워준다 
	- 구매 수량 입력 받기
> 2. 구매내역 - MemberCart

	[1] 쇼핑하기 	MemberCartBuy
	[2] 뒤로가기 	MemberMain
	[0] 종료		""
	- 현재 로그인중인 유저의 카트 목록 출력(아이템, 가격, 개수, 총 가격)
	- 쇼핑하기는 각각 가격 총 개수 총 가격 띄워줌
> 3. 게시판 - MemberBoard

	[1] 게시글 목록 	BoardList
	[2] 게시글 추가 	BoardAddPage
	[3] 게시글 삭제	BoardDelete
	[4] 뒤로가기 	MemberMain
	[0] 종료	""
	- 글쓰기 작성 시 날짜 받아오기 (LocalDate)
	- 게시글을 볼 때마다 조회수 1 증가
	- 해당 페이지에서만 선택 가능
	- 목록에서는 제목, 작성자, 날짜, 조회수

	- 게시글 목록 선택 시
	[1] 이전	BoardBefore
	[2] 이후	BoardAfter
	[3] 게시글보기	BoardViewPage
	[4] 뒤로가기	MemberBoard
	[0] 종료	""
	- 게시글 목록 선택 시  
	첫줄 제목 작성자 날짜 조회수, 두번째줄 내용
	- 본인 게시글만 삭제 가능
	- 내 게시글 선택 시 
	[1] 삭제	BoardDeletePage
	[2] 뒤로가기	MemberBoard
	[0] 종료	""
> 4. 나의 정보 - MemberInfo

	[1] 비밀번호변경 MemberInfoPwUpdate
	[2] 뒤로가기 	MemberMain
	[3] 종료	""
	- member 정보 출력
	- 비밀번호 변경 시 다시 비밀번호를 입력 받아야 변경 가능
	- 새 비밀번호와 겹치면 안됨
> 5. 회원 탈퇴 - MemberQuit

	- 내 정보, 장바구니 정보도 모두 삭제, 게시글을 삭제하지 않는다
> 6. 로그 아웃 - MallMain

	- id값 "" 로 변경 
> 0. 종료 - ""
