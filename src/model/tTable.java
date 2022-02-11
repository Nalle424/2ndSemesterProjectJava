package model;

public class tTable {
	
	private int tableNo;
	private int seats;
	private int customerID;
	
	public tTable(int tableNo, int seats, int customerID) {
		this.setTableNo(tableNo);
		this.setSeats(seats);
		this.setCustomerID(customerID);
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	

}
