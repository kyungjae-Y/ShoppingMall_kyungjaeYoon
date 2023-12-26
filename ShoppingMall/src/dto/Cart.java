package dto;

public class Cart {
	private static int num;
	private int cartNum;
	private String id;
	private int itemNum;
	private int itemCnt;

	public Cart() {
	}

	private Cart(String number, String id, String itemNumber, String itemCnt) {
		super();
		this.cartNum = Integer.parseInt(number);
		this.id = id;
		this.itemNum = Integer.parseInt(itemNumber);
		this.itemCnt = Integer.parseInt(itemCnt);
		cartNum++;
	}

	public Cart(String id, int itemNum) {
		this.cartNum = ++num;
		this.id = id;
		this.itemNum = itemNum;
	}
}