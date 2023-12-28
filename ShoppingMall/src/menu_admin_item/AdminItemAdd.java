package menu_admin_item;

import _mall.MenuCommand;
import controller.MallController;

public class AdminItemAdd implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		System.out.println("===== [ 관리자 - 카테고리별 아이템 목록 ] =====");
		return false;
	}
}
