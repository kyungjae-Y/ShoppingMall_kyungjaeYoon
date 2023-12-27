package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
//import dao.FileDAO;
import util.Util;

public class _AdminMain implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	public boolean update() {
		System.out.println("===== [ 관리자 ] =====");
		System.out.println("[1] 회원 관리");
		System.out.println("[2] 상품 관리");
		System.out.println("[3] 게시판 관리");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 파일 저장");
		System.out.println("[0] 종료");
		int sel = Util.getValue("메뉴 입력", 0, 5);
		if (sel == 0) {
			cont.setNext("");
		}
		if (sel == 1) {
			cont.setNext("AdminMember");
		} else if (sel == 2) {
			cont.setNext("AdminItem");
		} else if (sel == 3) {
			cont.setNext("AdminBoard");
		} else if (sel == 4) {
			cont.setNext("MallMain");
		} else if (sel == 5) {
//			FileDAO.getInstance().saveFile();
			System.out.println("저장 되었습니다");
		}
		return false;
	}
}
