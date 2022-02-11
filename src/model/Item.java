package model;

public class Item {
	
	private int itemID;
	private String name;
	private String type;
	private double price;
	private int amount;
	private int minAmount;
	
	
	public Item() {

	}
	
	public Item(int itemID, String name, String type, double price, int amount, int minAmount) {
		this.setItemID(itemID);
		this.setName(name);
		this.setType(type);
		this.setPrice(price);
		this.setAmount(minAmount);
		this.setMinAmount(minAmount);
	}

	/**
	 * meothed below here is get and set for Item 
	 * @return
	 */
	public int getItemID() {
		return itemID;
	}


	public void setItemID(int itemID) {
		this.itemID = itemID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getMinAmount() {
		return minAmount;
	}


	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}

}
