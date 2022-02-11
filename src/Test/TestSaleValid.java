package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;

import org.junit.*;

import control.BoardgameReservationCtr;
import control.DataAccessException;
import database.SaleDB;
import model.Sale;
import model.SaleLine;

public class TestSaleValid {
	private SaleDB sDB;
	private BoardgameReservationCtr RCon;
	private Sale testSale;
	private SaleLine testSL;
	private int testID;
	
	
	@Before
	public void setUp() throws DataAccessException {
		sDB = new SaleDB();
		RCon = new BoardgameReservationCtr();
		testSale = new Sale("", 1);
		testSL = new SaleLine(1, 1);		
		testSale.addSaleLine(testSL);
	}
	
	@Test
	public void test() throws DataAccessException {
		//Arrange
		testID = 0;
		int unexpectedInt = 0;
		//Act
		testID = RCon.createSale(testSale);
		//Assert
		assertTrue("testID should be always be larger than unexpectedInt,"
				+ "if transaction commits.", unexpectedInt < testID);
	}
	
	@After
	public void cleanUP() throws SQLException {
		sDB.deleteSaleAndSaleine(testID);
	}
	
	
}
