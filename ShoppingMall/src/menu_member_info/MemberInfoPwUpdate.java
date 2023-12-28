package menu_member_info;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MemberInfoPwUpdate implements MenuCommand {
	private MallController cont = MallController.getInstance();

	@Override
	public boolean update() {
		MemberDAO mDAO = MemberDAO.getInstance();
		cont.setNext("MemberMain");
		String pw = Util.getValue("PW : ");
		int idIdx = mDAO.idValue(cont.getId());
		int pwIdx = mDAO.pwValue(pw);
		if (pwIdx != idIdx) {
			System.out.println("[%s] 비밀번호가 틀렸습니다".formatted(cont.getId()));
			return false;
		}
		String newPw = Util.getValue("새 PW : ");
		if (pw.equals(newPw)) {
			System.out.println("[%s] 같은 비밀번호는 변경이 안됩니다".formatted(cont.getId()));
			return false;
		}
		mDAO.getmList().get(idIdx).setPw(newPw);
		System.out.println("[%s] 비밀번호 변경 완료".formatted(cont.getId()));
		return false;
	}
}