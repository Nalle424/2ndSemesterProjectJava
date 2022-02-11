package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import control.DataAccessException;
import model.GameTable;

public class TableDB implements TableDBIF {

	private static final String FIND_T = "SELECT tableNo FROM GameTable";
	private PreparedStatement findTable;
	
	public TableDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findTable = con.prepareStatement(FIND_T);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_Prepare_Statement, e);
		}
	}

	/**
	 * findTables method is the method that are used for finding all tables
	 */
	@Override
	public LinkedList<GameTable> findTables() throws DataAccessException {
		LinkedList<GameTable> res = null;
		try { 
			ResultSet rs = findTable.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY,e );
		}
		return res;
	}

	/**
	 * buidObject method is the method that builds the list object for other method to use
	 * @param rs
	 * @return
	 * @throws DataAccessException
	 */
	private LinkedList<GameTable> buildObjects(ResultSet rs) throws DataAccessException {
		LinkedList<GameTable> res = new LinkedList<>();
		try {
			while (rs.next()) {
				GameTable currTable = buildObject(rs);
				res.add(currTable);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	
	/**
	 * buildObject method is the method that build a single object for other method to use
	 * @param rs
	 * @return
	 * @throws DataAccessException
	 */
	private GameTable buildObject(ResultSet rs) throws DataAccessException {
		GameTable currTable = new GameTable();
		try {
			currTable.setTableNo(rs.getInt("tableNo"));
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		
		return currTable;
		
	}



	
}
