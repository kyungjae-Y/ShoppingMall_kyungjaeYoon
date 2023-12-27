package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class MemberCart implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("===== [ 구매내역 ] =====");
		System.out.println("[1] 쇼핑하기");
		System.out.println("[2] 뒤로가기");
		System.out.println("[0] 종료");
		int sel = Util.getValue("메뉴 입력", 0, 2);
		if (sel == 0) {
			cont.setNext("");
		} else if (sel == 1) {
			cont.setNext("MemberCartBuy");
		} else if (sel == 2) {
			cont.setNext("MemberMain");
		}
		return false;
	}
}
