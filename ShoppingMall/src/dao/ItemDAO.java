package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dto.Item;

public class ItemDAO {
	private ArrayList<Item> itemList;

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public ItemDAO() {
		itemList = new ArrayList<Item>();
	}

	public void loadData(String data) {
		String[] temp = data.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String[] info = temp[i].split("/");
			Item item = new Item(info[0], info[1], info[2], info[3]);
			itemList.add(item);
		}
	}

//	아이템 이름 중복 - 카테고리이름도 일치
	public int ItemNameValue(String cgName, String iName) {
		if (itemList.size() == 0)
			return -1;
		for (int i = 0; i < itemList.size(); i += 1) {
			String itemName = itemList.get(i).getItemName();
			String categoriName = itemList.get(i).getCategoriName();
			if (itemName.equals(iName) && categoriName.equals(cgName)) {
				return i;
			}
		}
		return -1;
	}

//	아이템 추가
	public void AddItem(String cgName, String iName, String price) {
		itemList.add(new Item(cgName, iName, price));
	}

//	아이템 수정
	public void UpdateItem(String cgName, String iName, String price, int idx) {
		Item data = itemList.get(idx);
		String[] info = { data.getItemNum() + "", cgName, iName, price };
		data = data.CreateItem(info);
		itemList.set(idx, data);
	}

//	아이템 삭제
	public void DeleteItem(int idx) {
		itemList.remove(idx);
	}

//	아이템 목록
	public ArrayList<Integer> ItemList(String cgName) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int cnt = 0;
		System.out.println("[" + cgName + " 목록]");
		for (int i = 0; i < itemList.size(); i += 1) {
			if (itemList.get(i).getCategoriName().equals(cgName)) {
				arr.add(itemList.get(i).getItemNum());
				System.out.println("[%d] %s".formatted(++cnt, itemList.get(i).printItem()));
			}
		}
		return arr;
	}

//	카테고리 수정
	public void UpdateCategory(String name, String newName) {
		if (itemList.size() == 0)
			return;
		for (int i = 0; i < itemList.size(); i += 1) {
			if (itemList.get(i).getCategoriName().equals(name)) {
				itemList.get(i).setCategoriName(newName);
			}
		}
	}

//	카테고리 삭제
	public void DeleteCategory(String name) {
		if (itemList.size() == 0)
			return;
		for (int i = 0; i < itemList.size(); i += 1) {
			if (itemList.get(i).getCategoriName().equals(name)) {
				itemList.remove(i);
				i--;
			}
		}
	}

//	카테고리 이름 받아서 카테고리에 속하는 아이템 넘버 리스트 반납
	public ArrayList<Integer> CategoriToItemList(String cgName) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < itemList.size(); i += 1) {
			if (itemList.get(i).getCategoriName().equals(cgName)) {
				arr.add(itemList.get(i).getItemNum());
			}
		}
		return arr;
	}

//	텍스트 파일 저장용 데이터 만들기
	public String DataToFile() {
		String data = "";
		if (itemList.size() == 0)
			return data;
		for (Item i : itemList) {
			data += i.DataToFile() + "\n";
		}
		data = data.substring(0, data.length() - 1);
		return data;
	}

//	아이템 넘버로 카테고리 이름 찾아서 반환
	public String getCategoryName(int itemNum) {
		String name = "";
		for (int i = 0; i < itemList.size(); i += 1) {
			if (itemList.get(i).getItemNum() == itemNum) {
				name = itemList.get(i).toString();
			}
		}
		return name;
	}

//	로드할떄 아이템을 넣은 후에 카테고리만 중복없이 가져오기
	public ArrayList<String> getCategoryList() {
		Set<String> cgList = new HashSet<String>();
		for (int i = 0; i < itemList.size(); i += 1) {
			cgList.add(itemList.get(i).getCategoriName());
		}
		ArrayList<String> list = new ArrayList<>(cgList);
		return list;
	}
}