package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.Item;

public class ItemDAO {
	private static ItemDAO instance = new ItemDAO();
	private static ArrayList<Item> itemList;
	private static ArrayList<String> categoryList;

	public ItemDAO() {
		itemList = new ArrayList<Item>();
		categoryList = new ArrayList<String>();
	}

	public static ItemDAO getInstance() {
		return instance;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public static ArrayList<String> getCategoryList() {
		return categoryList;
	}

	public static void setCategoryList(ArrayList<String> categoryList) {
		ItemDAO.categoryList = categoryList;
	}

//	아이템 추가
	public void AddItem(String cgName, String iName, String price) {
		itemList.add(new Item(cgName, iName, price));
	}

//	아이템 삭제
	public void DeleteItem(int itemNum) {
		for (int i = 0; i < itemList.size(); i += 1) {
			if (itemList.get(i).getItemNum() == itemNum) {
				itemList.remove(i);
				break;
			}
		}
	}

//	텍스트 파일 저장용 데이터 만들기
	public static String DataToFile() {
		String data = "";
		if (itemList.size() == 0)
			return data;
		for (Item i : itemList) {
			data += i.DataToFile() + "\n";
		}
		data = data.substring(0, data.length() - 1);
		return data;
	}

//	텍스트 파일에서 문자열 받아와서 데이터 넣기 - 셋에 카테고리 넣어서 전달
	public static void FileToData(List<String> iData) {
		if (iData.isEmpty())
			return;
		int maxItemNum = 0;
		for (int i = 0; i < iData.size(); i += 1) {
			Item item = new Item();
			String[] info = iData.get(i).split("/");
			item = item.CreateItem(info);
			itemList.add(item);
			if (maxItemNum < Integer.parseInt(info[0])) {
				maxItemNum = Integer.parseInt(info[0]);
			}
		}
		Item.setNum(maxItemNum);
		categoryList = FileToDataCategory();
	}

//	카테고리 선택하면 해당 이름 반환
	public String getCategoryName(int categoryidx) {
		return categoryList.get(categoryidx);
	}

//	카테고리 내림차순(같다면 아이템 기준 내림차순) 정렬해서 출력
	public void itemListPrint() {
		System.out.println("===[ 카테고리별 아이템 목록]===");
		itemList.stream().sorted(Comparator.comparing(Item::getCategoryName).thenComparing(Item::getItemName))
				.forEach(System.out::println);
	}

//	로드할때 아이템을 넣은 후에 카테고리만 중복없이 가져오기
	public static ArrayList<String> FileToDataCategory() {
		Set<String> categoryList = new HashSet<String>();
		for (int i = 0; i < itemList.size(); i += 1) {
			categoryList.add(itemList.get(i).getCategoryName());
		}
		ArrayList<String> list = new ArrayList<>(categoryList);
		return list;
	}

//	아이템 추가시 카테고리 검사하고 추가
	public void addCategory(String categoryName) {
		long cnt = categoryList.stream().filter(c -> c.equals(categoryName)).count();
		if (cnt == 0) {
			categoryList.add(categoryName);
		}
	}

//	카테고리 리스트 출력
	public void CategoryList() {
		for (int i = 0; i < categoryList.size(); i += 1) {
			System.out.println("[%d] %s".formatted(i + 1, categoryList.get(i)));
		}
	}

//	카테고리 이름 받아서 카테고리에 속하는 아이템 넘버 리스트 반납
//	여기서 출력으로 리스트도 보여줌
	public ArrayList<Item> CategoriToItemList(String cgName) {
		ArrayList<Item> cgToItemList = new ArrayList<Item>();
		int cnt = 1;
		for (int i = 0; i < itemList.size(); i += 1) {
			if (itemList.get(i).getCategoryName().equals(cgName)) {
				cgToItemList.add(itemList.get(i));
				System.out.println(
						"[%d] %s %d원".formatted(cnt++, itemList.get(i).getItemName(), itemList.get(i).getPrice()));
			}
		}
		return cgToItemList;
	}

//	아이템 넘버로 아이템 찾아서 넘겨주는 메서드
	public Item itemVelue(int itemNum) {
		for (int i = 0; i < itemList.size(); i += 1) {
			if (itemList.get(i).getItemNum() == itemNum) {
				return itemList.get(i);
			}
		}
		return null;
	}

//	아이템 번호 받아서 전달할 메서드
	public int[][] itemNumList() {
		int[][] arr = new int[itemList.size()][4];
		for (int i = 0; i < arr.length; i += 1) {
			arr[i][0] = i; // 방번호
			arr[i][1] = itemList.get(i).getItemNum();
		}
		return arr;
	}

//	카트에서 팔린 개수 받아와서 순위 오름차순 출력
	public void ItemRevenue(int[][] arr) {
//		순위로 정렬해보고
		for (int i = 0; i < arr.length; i += 1) {
			for (int k = 0; k < arr.length; k += 1) {
				if (arr[i][3] < arr[k][3]) {
					int[] data = arr[i];
					arr[i] = arr[k];
					arr[k] = data;
				}
			}
		}
		for (int i = 0; i < arr.length; i += 1) {
			if (arr[i][2] == 0)
				break;
			System.out.printf("%s %d개\n", itemList.get(arr[i][0]), arr[i][2]);
		}
	}
}