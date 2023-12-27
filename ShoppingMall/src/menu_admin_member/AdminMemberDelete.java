package menu_admin_member;

import _mall.MenuCommand;
//import controller.MallController;

public class AdminMemberDelete implements MenuCommand {
//	private MallController cont;

	@Override
	public void init() {
//		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("===== [ 관리자 - 전체 회원 목록 삭제 ] =====");
		return false;
	}
}
