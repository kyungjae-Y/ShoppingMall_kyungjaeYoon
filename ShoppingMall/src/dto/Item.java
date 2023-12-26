package dto;

public class Item {
	private static int num;
	private int itemNum;
	private String categoryName;
	private String itemName;
	private int price;

	public Item() {
	}

	private Item(String number, String categoryName, String itemName, String price) {
		super();
		this.itemNum = Integer.parseInt(number);
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = Integer.parseInt(price);
		num++;
	}

	public Item(String categoryName, String itemName, int price) {
		super();
		this.itemNum = ++num;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = price;
	}
}