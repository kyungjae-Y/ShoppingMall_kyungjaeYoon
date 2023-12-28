package menu_admin_item;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;

public class AdminItemRevenue implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		ItemDAO iDAO = ItemDAO.getInstance();
		CartDAO cDAO = CartDAO.getInstance();
		cont.setNext("AdminItem");
//		ItemDAO 에서 일단 itemNum 개수만큼 번호도 저장해서 배열로 받아옴
		int[][] arr = iDAO.itemNumList();
		cDAO.ItemRevenue(arr);
//		arr[itemList.size()][4]
//		0번 - 아이템 리스트 방번호
//		1번 - 아이템 넘버
//		2번 - 카트에서 받아온 팔린 개수
//		3번 - 랭크
		iDAO.ItemRevenue(arr);
		return false;
	}
}