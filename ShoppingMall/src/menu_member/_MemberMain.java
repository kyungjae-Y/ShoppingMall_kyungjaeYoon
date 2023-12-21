package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _MemberMain implements MenuCommand {
	private MallController mallCont;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
	}

	public boolean update() {
		System.out.println("[1] 상품 구매");
		System.out.println("[2] 장바구니");
		System.out.println("[3] 게시판");
		System.out.println("[4] 나의 정보");
		System.out.println("[5] 회원 탈퇴");
		System.out.println("[0] 로그아웃");
		int sel = Util.getValue("메뉴 입력", 0, 5);
		if (sel == 0) {
			mallCont.setNext("MallMain");
		} else if (sel == 1) {
			mallCont.setNext("MemberShopping");
		} else if (sel == 2) {
			mallCont.setNext("MemberCart");
		} else if (sel == 3) {
			mallCont.setNext("MemberBoard");
		} else if (sel == 4) {
			mallCont.setNext("MemberMy");
		} else if (sel == 5) {
			mallCont.setNext("MemberEx");
		}
		return false;
	}
}
