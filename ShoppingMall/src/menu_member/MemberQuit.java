package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;

public class MemberQuit implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		MemberDAO mDAO = MemberDAO.getInstance();
		CartDAO cDAO = CartDAO.getInstance();
		cont.setNext("MallMain");
		int idIdx = mDAO.idValue(cont.getId());
//		멤버리스트에서 id 삭제
		mDAO.MemberDelete(idIdx);
//		카트에서도 id 와 일치하는 값 삭제
		cDAO.MemberDelete(cont.getId());
		System.out.println("[%s] 회원 탈퇴 완료".formatted(cont.getId()));
		cont.setId("");
		return false;
	}
}