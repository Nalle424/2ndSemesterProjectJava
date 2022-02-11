package database;

import java.util.LinkedList;

import control.DataAccessException;
import model.Item;
import model.Sale;
import model.SaleLine;

public interface SaleDBIF {
	
	int createSale(Sale currSale) throws DataAccessException;
	void saleLine(Sale sale, int tableNo) throws DataAccessException;
	
}
