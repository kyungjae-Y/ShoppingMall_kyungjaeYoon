package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class MallJoin implements MenuCommand {
	private MallController mallCont;

	public MallJoin() {
		init();
	}

	@Override
	public void init() {
		mallCont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("===[ 널이다 ]===");
		System.out.println("[1] 회원가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 종료");
		int sel = Util.getValue("메뉴 입력", 0, 2);

		if (sel == 0) {
			System.out.println("프로그램 종료");
			return false;
		} else if (sel == 1) {
			mallCont.setNext("MallJoin").update();
		} else if (sel == 2) {
			mallCont.setNext("MallLogin").update();
		}
		return true;
	}
}
