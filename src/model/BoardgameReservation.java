package model;

public class BoardgameReservation {
	
	private int orderNo;
	private int reserveGameID;
	private int cusID;
	private int tableNo;
	
	
	
	public BoardgameReservation(int orderNo, int reserveGameID, int cusID, int tableNo) {
		this.setOrderNo(orderNo);
		this.setReserveGameID(reserveGameID);
		this.setCusID(cusID);
		this.setTableNo(tableNo);
		
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getReserveGameID() {
		return reserveGameID;
	}

	public void setReserveGameID(int reserveGameID) {
		this.reserveGameID = reserveGameID;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

}
