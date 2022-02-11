package model;

public class SaleLine {
	
	private int amount;
	private int itemID;
	private int saleNo;
	private int saleLineID;
	
	/**
	 * constuctor need the following parameters to work ItemID is the id for af given item 
	 * @param itemID
	 * @param amount
	 */
	public SaleLine(int itemID, int amount) {
		this.setAmount(amount);
		this.setItemID(itemID);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(int saleNo) {
		this.saleNo = saleNo;
	}

	public int getSaleLineID() {
		return saleLineID;
	}

	public void setSaleLineID(int saleLineID) {
		this.saleLineID = saleLineID;
	}

}
