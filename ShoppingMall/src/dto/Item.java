package dto;

public class Item {
	private static int num;
	private int itemNum;
	private String categoryName;
	private String itemName;
	private int price;

	public Item() {
	}

	public static void setNum(int num) {
		Item.num = num;
	}

	public int getItemNum() {
		return itemNum;
	}

	public String getCategoriName() {
		return categoryName;
	}

	public void setCategoriName(String categoriName) {
		this.categoryName = categoriName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public Item(String categoriName, String itemName, String price) {
		this.itemNum = ++num;
		this.categoryName = categoriName;
		this.itemName = itemName;
		this.price = Integer.parseInt(price);
	}

	public Item(String itemNum, String categoryName, String itemName, String price) {
		this.itemNum = Integer.parseInt(itemNum);
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = Integer.parseInt(price);
	}

	public Item CreateItem(String[] info) {
		if (info == null || info.length == 0)
			return null;

		return new Item(info[0], info[1], info[2], info[3]);
	}

	public String printItem() {
		return "%s".formatted(itemName);
	}

	@Override
	public String toString() {
		return categoryName + " " + itemName + " ";
	}

	public String DataToFile() {
		return "%d/%s/%s/%d".formatted(itemNum, categoryName, itemName, price);
	}
}