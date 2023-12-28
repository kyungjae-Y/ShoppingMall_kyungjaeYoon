package menu_admin_item;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class AdminItemDelete implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		ItemDAO iDAO = ItemDAO.getInstance();
		CartDAO cDAO = CartDAO.getInstance();
		cont.setNext("AdminItem");
		iDAO.itemListPrint();
		System.out.println("[관리자] 아이템 삭제시 구매내력이 사라집니다");
		int itemNum = Util.getValue("번호 입력 : ", 1, iDAO.getItemList().size());
		if (itemNum == -1) {
			return false;
		}
		iDAO.DeleteItem(itemNum);
		cDAO.DeleteItem(itemNum);
		System.out.println("[관리자] 아이템 삭제 완료");
		return false;
	}
}