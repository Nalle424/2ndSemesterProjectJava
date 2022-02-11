package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;


import control.DataAccessException;

import model.Sale;


public class SaleDB implements SaleDBIF {


	private static final String CREATE_ORDER = "INSERT INTO Sale (tableNo, note) VALUES(?,?) SELECT SCOPE_IDENTITY() as id";
	private PreparedStatement createSale;
	
	private static final String ADD_SALELINE = "INSERT INTO SalesLine (itemID, amount, salesNo) VALUES(?,?,?)";
	private PreparedStatement addSaleLine;
	
	private static final String DELETE_SALE = "DELETE Sale where salesNo = ?";
	private PreparedStatement deleteSale;
	
	private static final String DELETE_SALELINE = "DELETE SalesLine where salesNo = ?";
	private PreparedStatement deleteSaleline;
	
	
	
	
	
	public SaleDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			createSale = con.prepareStatement(CREATE_ORDER);
			addSaleLine = con.prepareStatement(ADD_SALELINE);
			deleteSale = con.prepareStatement(DELETE_SALE);
			deleteSaleline = con.prepareStatement(DELETE_SALELINE);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	/**
	 * Method that handle the insert sale 
	 * @return 
	 * @return 
	 */
	@Override
	public int createSale(Sale sale) throws DataAccessException {
		int saleID = 0;
		try {
			createSale.setInt(1, sale.getTableNo());
			createSale.setString(2, sale.getNote());
						
			createSale.executeUpdate();
			ResultSet saleNo = createSale.getGeneratedKeys();
			saleNo.next();
			saleID = saleNo.getInt("id");
			saleLine(sale, saleID);
		}catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_ADD_SALE, e);
		}
		return saleID;
	}
	
	/**
	 * Method for handling the saleline of the sale which contain all items the sale have
	 */
	public void saleLine(Sale sale,int saleNo) throws DataAccessException {
		sale.getSL().forEach((salesLine) -> {
			try {
				addSaleLine.setInt(1, salesLine.getItemID());
				addSaleLine.setInt(2, salesLine.getAmount());
				addSaleLine.setInt(3, saleNo);	
				addSaleLine.executeUpdate();
			} catch (SQLException e) {
				//throw new DataAccessException(DBMessages.CLOUD_NOT_ADD_SALELINE, e);
			}
		});
	}	
	
	
	public void deleteSaleAndSaleine(int testID) throws SQLException {
		deleteSale.setInt(1, testID);
		deleteSaleline.setInt(1, testID);
		
		
		deleteSaleline.executeUpdate();
		deleteSale.executeUpdate();
		
	}
}
