package menu_admin_member;

import _mall.MenuCommand;
import controller.MallController;

public class AdminMemberList implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		System.out.println("===== [ 관리자 - 전체 회원 목록 ] =====");
		return false;
	}
}
