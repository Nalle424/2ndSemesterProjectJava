package database;

import control.DataAccessException;
import database.DBMessages;
import model.Boardgame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.SQLException;

public class BoardgameReservationDB implements BoardgameReservationDBIF {
	
	private static final String INSERT_BGR_Q = "INSERT INTO GameReservation (reservedGameID, tableNo) VALUES(?,?)";
	private PreparedStatement insertBGR;
	private static final String INSERT_BGR_Qs = "INSERT INTO GameReservation (reservedGameID, cusID, tableNo) VALUES(?,?,?)";
	private PreparedStatement insertBGRs;
	
	public BoardgameReservationDB() throws DataAccessException {
	
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertBGR = con.prepareStatement(INSERT_BGR_Q);
			insertBGRs = con.prepareStatement(INSERT_BGR_Qs);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_Prepare_Statement, e);
		}
		
	}
	
	/**
	 * Method for handling reservation for the boardgames to the table with a customer
	 * @param reserveGameID
	 * @param cusID
	 * @param tableNo 
	 */
	@Override
	public boolean reservedBoardgame(int reserveGameID,int cusID, int tableNo) throws DataAccessException {
		// TODO Auto-generated method stub
		boolean booCheck = false;
		try {
			insertBGRs.setInt(1,reserveGameID);
			insertBGRs.setInt(2,cusID);
			insertBGRs.setInt(3,tableNo);
		
		
		} catch (SQLException e) {
			
		}
		try {
			insertBGRs.executeUpdate();
			booCheck = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_Reserve_Game, e);
		}
		return booCheck;
	}
	
	/**
	 * Method for handling reservation of boardgames to the table
	 * @param reserveGameID
	 * @param tableNo
	 */
	@Override
	public boolean reservedBoardgame(int reserveGameID, int tableNo) throws DataAccessException {
		// TODO Auto-generated method stub
		boolean booCheck = false;
		try {
			insertBGR.setInt(1,reserveGameID);
			insertBGR.setInt(2,tableNo);
		
		} catch (SQLException e) {
			System.out.println("didnt do jack shit");
		}
		try {
			insertBGR.executeUpdate();
			booCheck = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_Reserve_Game, e);
		}
		return booCheck;
	}

}
