package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Cart;
import dto.Item;

public class CartDAO {
	private static CartDAO instance = new CartDAO();
	private static ArrayList<Cart> cList;

	public CartDAO() {
		cList = new ArrayList<Cart>();
	}

	public static CartDAO getInstance() {
		return instance;
	}

	public ArrayList<Cart> getcList() {
		return cList;
	}

//	회원 삭제 시 카트 날리기
	public void MemberDelete(String id) {
		if (cList.size() == 0)
			return;
		for (int i = 0; i < cList.size(); i += 1) {
			if (cList.get(i).getId().equals(id)) {
				cList.remove(i);
				i--;
			}
		}
	}

//	아이템 삭제 시 카트 아이템도 삭제
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

//	텍스트 파일 저장용 데이터 만들기
	public static String DataToFile() {
		String data = "";
		if (cList.size() == 0)
			return data;
		for (Cart c : cList) {
			data += c.DataToFile() + "\n";
		}
		data = data.substring(0, data.length() - 1);
		return data;
	}

//	텍스트 파일에서 문자열 받아와서 데이터 넣기
	public static void FileToData(List<String> cData) {
		if (cData.isEmpty())
			return;
		int maxCartNum = 0;
		for (int i = 0; i < cData.size(); i += 1) {
			Cart c = new Cart();
			String[] info = cData.get(i).split("/");
			c = c.CreateCart(info);
			cList.add(c);
			if (maxCartNum < Integer.parseInt(info[0])) {
				maxCartNum = Integer.parseInt(info[0]);
			}
		}
		Cart.setNum(maxCartNum);
	}

//	id 하고 itemNum 받아서 일치하는 값 내보내기
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

//	해당 로그인한 유저의 구매목록 출력 - 아이템 이름, 가격, 개수, 총 금액
	public boolean getMyCartList(String id) {
		ItemDAO iDAO = ItemDAO.getInstance();
		int cnt = 0;
		int sum = 0;
		int total = 0;
		for (int i = 0; i < cList.size(); i += 1) {
			if (cList.get(i).getId().equals(id)) {
				Item myItem = iDAO.itemVelue(cList.get(i).getItemNum());
				int sumMoney = cList.get(i).getItemCnt() * myItem.getPrice();
				System.out.println("[%d] %s (%d원) %2d개 총 %7d원".formatted(++cnt, myItem.getItemName(), myItem.getPrice(),
						cList.get(i).getItemCnt(), sumMoney));
				total += sumMoney;
				sum += cList.get(i).getItemCnt();
			}
		}
		System.out.println("총 %d개 (%d원)".formatted(sum, total));
		return cnt == 0 ? true : false;
	}

//	장바구니 비우기 - 멤버용 : info[0] categoryName, info[1] itemName
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

//	카트에 저장되어 있는 아이템 개수 받아오기
	public void ItemRevenue(int[][] arr) {
		for (Cart c : cList) {
			for (int i = 0; i < arr.length; i++) {
//				itemNum이 일치하면 개수 추가
				if (c.getItemNum() == arr[i][1]) {
					arr[i][2] += c.getItemCnt();
					break;
				}
			}
		}
//		순위도 여기서 매기고 - 아이템 개수 기준
		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < arr.length; k += 1) {
				if (arr[i][2] <= arr[k][2]) {
					arr[i][3] += 1;
				}
			}
		}
	}
}