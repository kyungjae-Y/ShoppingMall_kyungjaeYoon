package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import util.Util;

public class MemberCart implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		CartDAO cDAO = CartDAO.getInstance();
		cont.setNext("MemberCart");
		if (cDAO.getcList().size() == 0) {
			System.out.println("[%s] 상품이 존재하지 않습니다".formatted(cont.getId()));
			cont.setNext("MemberMain");
			return false;
		}
		System.out.println("===== [ 구매내역 ] =====");
//		아무것도 없으면 true 반납
		if (cDAO.getMyCartList(cont.getId())) {
			System.out.println("[%s] 장바구니에 상품이 없습니다".formatted(cont.getId()));
			cont.setNext("MemberMain");
			return false;
		}
		System.out.println("[1] 쇼핑하기");
		System.out.println("[2] 뒤로가기");
		System.out.println("[0] 종료");
		int sel = Util.getValue("선택", 0, 2);
		if (sel == 1) {
			System.out.println("상품을 구매해 주셔서 감사합니다");
			cDAO.MemberDelete(cont.getId());
			cont.setNext("MemberMain");
		} else if (sel == 2) {
			cont.setNext("MemberMain");
		} else if (sel == 0) {
			cont.setNext("");
		}
		return false;
	}
}