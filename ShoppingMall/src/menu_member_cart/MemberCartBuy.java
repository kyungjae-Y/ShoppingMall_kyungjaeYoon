package menu_member_cart;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class MemberCartBuy implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("===== 쇼핑몰에 오신 것을 환영합니다 =====");
		System.out.println("[1] 과자");
		System.out.println("[2] 생선");
		System.out.println("[3] 음료");
		System.out.println("[4] 고기");
		System.out.println("[5] 과일");
		System.out.println("[0] 뒤로가기");
		int sel = Util.getValue("메뉴 입력", 0, 5);
		if (sel == 0) {
			cont.setNext("MemberMain");
		} else if (sel == 1) {
			System.out.println("[ 과자의 아이템 목록 ]");
		} else if (sel == 2) {
			System.out.println("[ 생선의 아이템 목록 ]");
		} else if (sel == 3) {
			System.out.println("[ 음료의 아이템 목록 ]");
		} else if (sel == 4) {
			System.out.println("[ 고기의 아이템 목록 ]");
		} else if (sel == 5) {
			System.out.println("[ 과일의 아이템 목록 ]");
		}
		return false;
	}
}
