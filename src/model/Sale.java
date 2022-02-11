package model;
import java.util.LinkedList;
public class Sale {
	
	private int saleNo;
	private String note;
	private int tableNo;
	private LinkedList<SaleLine> sl;
	
	/**
	 * constructor for sale that only start a new saleline
	 */
	public Sale() {
		sl = new LinkedList<SaleLine>();
	}
	
	/**
	 * constructor for sale
	 * @param note
	 * @param tableNo
	 */
	public Sale(String note, int tableNo) {
		sl = new LinkedList<SaleLine>();
		this.setNote(note);
		this.setTableNo(tableNo);
		this.sl = new LinkedList<SaleLine>();
		
	}
	
	public int getSaleNo() {
		return saleNo;
	}

	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public int getTableNo() {
		return tableNo;
	}


	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	
	/**
	 * Method for adding new saleline to the currensale
	 * @param saleline
	 */
	public void addSaleLine(SaleLine saleline){
		sl.add(saleline);
	}
	
	public LinkedList<SaleLine> getSL(){
		return sl;
	}
	
	/**
	 * to clear the current salelines 
	 * @param sale
	 */
	public void clear(Sale sale) {
		sale.sl.clear();
	}

}
