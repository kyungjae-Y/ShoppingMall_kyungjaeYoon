package dao;

import java.util.ArrayList;

import controller.MallController;
import dto.Cart;

public class CartDAO {
	private ArrayList<Cart> cList;
	private MallController cont;

	public ArrayList<Cart> getcList() {
		return cList;
	}

	public CartDAO() {
		cont = MallController.getInstance();
		cList = new ArrayList<Cart>();
	}

	public void loadData(String data) {
		String[] temp = data.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String[] info = temp[i].split("/");
			Cart c = new Cart(info[0], info[1], info[2], info[3]);
			cList.add(c);
		}
	}

//	회원 삭제 시 카트 날리기
	public void DeleteMember(String id) {
		if (cList.size() == 0)
			return;

		for (int i = 0; i < cList.size(); i += 1) {
			if (cList.get(i).getId().equals(id)) {
				cList.remove(i);
				i--;
			}
		}
	}

//	아이템 삭제시 카트 아이템도 삭제
	public void DeleteItem(int itemNum) {
		if (cList.size() == 0)
			return;
		for (int i = 0; i < cList.size(); i += 1) {
			if (cList.get(i).getItemNum() == itemNum) {
				cList.remove(i);
				i--;
			}
		}
	}

//	카테고리 삭제시 안에 있는 아이템 넘버들 받아와서 다 삭제
	public void DeleteCategory(ArrayList<Integer> iNumList) {
		if (iNumList.size() == 0)
			return;
		for (int i = 0; i < cList.size(); i += 1) {
			for (int k = 0; k < iNumList.size(); k += 1) {
				if (cList.get(i).getItemNum() == iNumList.get(k)) {
					cList.remove(i);
					i--;
					break;
				}
			}
		}
	}

//	텍스트 파일 저장용 데이터 만들기
	public String DataToFile() {
		String data = "";
		if (cList.size() == 0)
			return data;
		for (Cart c : cList) {
			data += c.DataToFile() + "\n";
		}
		data = data.substring(0, data.length() - 1);
		return data;
	}

//	텍스트파일에서 문자열 받아와서 데이터 넣기
	public void FileToData(String data) {
		if (data.equals(""))
			return;
		String datas[] = data.split("\n");
		cList.clear();
		int maxCartNum = 0;
		for (int i = 0; i < datas.length; i += 1) {
			Cart c = new Cart();
			String[] info = datas[i].split("/");
			c = c.CreateCart(info);
			cList.add(c);
			if (maxCartNum < Integer.parseInt(info[0])) {
				maxCartNum = Integer.parseInt(info[0]);
			}
		}
		Cart.setNum(maxCartNum);
	}

//	id하고 itemNum 받아서 일치하는 값 내보내기
	public int cartNumValue(String id, int itemNum) {
		if (cList.size() == 0)
			return -1;

		for (int i = 0; i < cList.size(); i += 1) {
			if (cList.get(i).getId().equals(id) && cList.get(i).getItemNum() == itemNum) {
				return i;
			}
		}

		return -1;
	}

//	해당 로그인한 유저의 장바구니 목록 출력 - 카테고리 이름, 아이템 이름, 개수
	public ArrayList<Integer> getMyCartList(String id) {
		int cnt = 0;
		ArrayList<Integer> nameList = new ArrayList<Integer>();
		System.out.println("[%s] 장바구니 목록".formatted(id));
		for (int i = 0; i < cList.size(); i += 1) {
			if (cList.get(i).getId().equals(id)) {
				String cgName = cont.getiDAO().getCategoryName(cList.get(i).getItemNum());
				nameList.add(cList.get(i).getCartNum());
				System.out.println("[" + (++cnt) + "]" + cgName + cList.get(i));
			}
		}
		return nameList;
	}

//	장바구니 1개 아이템 삭제 - 맴버용 : info[0] categoryName, info[1] itemName
	public void MemberCartOneDelete(int cartNum) {
		for (int i = 0; i < cList.size(); i += 1) {
			if (cartNum == cList.get(i).getCartNum()) {
				cList.remove(i);
				break;
			}
		}
	}

//	장바구니 비우기 - 맴버용 : info[0] categoryName, info[1] itemName
	public void MemberCartAllDelete(ArrayList<Integer> nameList) {
		for (int i = 0; i < nameList.size(); i += 1) {
			for (int k = 0; k < cList.size(); k += 1) {
				if (nameList.get(i) == cList.get(k).getCartNum()) {
					cList.remove(k);
					break;
				}
			}
		}
	}

//	내 장바구니에 있는 개수 반환
	public void MyCartNum(String id) {
		int cnt = 0;
		for (int i = 0; i < cList.size(); i += 1) {
			if (cList.get(i).getId().equals(id)) {
				cnt++;
			}
		}
		System.out.println("장바구니 개수 : %d개".formatted(cnt));
	}
}