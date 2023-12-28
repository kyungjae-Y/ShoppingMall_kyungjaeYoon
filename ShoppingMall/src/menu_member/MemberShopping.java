package menu_member;

import java.util.ArrayList;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dto.Cart;
import dto.Item;
import util.Util;

public class MemberShopping implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		CartDAO cDAO = CartDAO.getInstance();
		ItemDAO iDAO = ItemDAO.getInstance();
		cont.setNext("MemberMain");
		if (ItemDAO.getCategoryList().size() == 0) {
			System.out.println("[%s] 상품이 존재하지 않습니다".formatted(cont.getId()));
			return false;
		}
		System.out.println("===== [ 쇼핑몰에 오신것을 환영합니다 ] =====");
		iDAO.CategoryList();
		int cgSize = ItemDAO.getCategoryList().size();
//		카테고리 선택
		int categoryidx = Util.getValue("선택", 1, cgSize) - 1;
		if (categoryidx == -2)
			return false;
//		카테고리 이름 받아옴 ItemDAO에서 카테고리 이름하고 일치하는 아이템 리스트도
		String categoryName = iDAO.getCategoryName(categoryidx);
		ArrayList<Item> cgToItemList = iDAO.CategoriToItemList(categoryName);
//		아이템 선택
		int itemIdx = Util.getValue("선택", 1, cgToItemList.size()) - 1;
		if (itemIdx == -2)
			return false;
//		수량 선택
		int cnt = Util.getValue("아이템 구매 수량", 1, 100);
		if (cnt == -1)
			return false;
//		기존 카트에 같은 물건이 있는지 검사
		int cartNum = cDAO.cartNumValue(cont.getId(), cgToItemList.get(itemIdx).getItemNum());
		if (cartNum != -1) {
			Cart info = cDAO.getcList().get(cartNum);
			info.setItemCnt(info.getItemCnt() + cnt);
			System.out.println("[ %s %d개 구매 완료 ]".formatted(cgToItemList.get(itemIdx).getItemName(), cnt));
			return false;
		}
		Cart cart = new Cart(cont.getId(), cgToItemList.get(itemIdx).getItemNum() + "", cnt + ""); // 없다면 add
		cDAO.getcList().add(cart);

		System.out.println("[ %s %d개 구매 완료 ]".formatted(cgToItemList.get(itemIdx).getItemName(), cnt));
		return false;
	}

}