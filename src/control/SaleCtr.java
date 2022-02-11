package control;

import database.SaleDBIF;
import database.DBConnection;
import database.DBMessages;
import database.SaleDB;
import model.Sale;
import model.SaleLine;

public class SaleCtr {

	private SaleDBIF sDB;
	private DBConnection con;
	private Sale sales;
	
	
	
	public SaleCtr() throws DataAccessException {
		sDB = new SaleDB();
	}
	
	/**
	 * Method to create sale and insert sale and saleline into the database.
	 * @param currSale
	 * @return 
	 * @throws DataAccessException
	 */
	public int createSale(Sale currSale) throws DataAccessException {
		int testID = 0;
		con = DBConnection.getInstance();
		try {
			con.startTransaction();
			testID = sDB.createSale(currSale);
			con.commitTransaction();
			
		} catch (DataAccessException e) {
			con.rollbackTransaction();
			testID = 0;
			throw new DataAccessException(DBMessages.COULD_NOT_CREATE_SALE, e);
		}
		return testID;
	}
	
	/**
	 * Method for adding SaleLine into the Sale.
	 * @param sale
	 */
	public void additems(SaleLine sale) {
		sales.addSaleLine(sale);
	}
	
	/**
	 * Method for clearing a Sale of salelines.
	 * @param sale
	 */
	public void cancel(Sale sale) {
		sale.clear(sale);
	}
}
