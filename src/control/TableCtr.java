package control;

import java.util.LinkedList;

import database.TableDB;
import database.TableDBIF;
import model.GameTable;

public class TableCtr {
	private TableDBIF tableDB;
	
	public TableCtr() throws DataAccessException {
		tableDB = new TableDB();
	}

	/**
	 * Method for finding and creatinga list of tables from the database.
	 * @return
	 * @throws DataAccessException
	 */
	public LinkedList<GameTable> findTables() throws DataAccessException {
		LinkedList<GameTable> res = null;
		res = tableDB.findTables(); 
		return res;
		
	}

}
