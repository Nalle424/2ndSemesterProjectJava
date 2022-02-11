package control;

import database.BoardgameReservationDBIF;
import database.DBConnection;
import database.DBMessages;

import java.util.LinkedList;

import database.BoardgameReservationDB;
import model.Boardgame;
import model.Sale;

public class BoardgameReservationCtr {
	
	private BoardgameReservationDBIF bgDB;
	private BoardgameCtr bgCon;
	private DBConnection con;
	private SaleCtr sCtr;
	
	public BoardgameReservationCtr() throws DataAccessException {
		bgDB = new BoardgameReservationDB();
		bgCon = new BoardgameCtr();
		sCtr = new SaleCtr();
	}
	
	/**
	 * Method for searching for boardgames.
	 * @param name
	 * @return
	 * @throws DataAccessException
	 */
	public LinkedList<Boardgame> searchBoardgame (String name) throws DataAccessException {
		LinkedList<Boardgame> exists = null;
		exists = bgCon.searchBoardgame(name);
		return exists;
	}
	
	/**
	 * Method for reserving the currently selected boardgame.
	 * @param reserveGameID
	 * @param cusID
	 * @param tableNo
	 * @return
	 * @throws DataAccessException
	 */
	public boolean reservedBoardgame(int reserveGameID, int cusID, int tableNo) throws DataAccessException {
		boolean reserved = false;
		con = DBConnection.getInstance();
		try { 
			con.startTransaction();
		
			if (cusID == 0) {
				reserved = bgDB.reservedBoardgame(reserveGameID, tableNo);
			}
			else {
				reserved = bgDB.reservedBoardgame(reserveGameID, cusID, tableNo);	
			}
			bgCon.reduceAmount(reserveGameID);
			con.commitTransaction();
		} catch (DataAccessException e) {
			con.rollbackTransaction();
			throw new DataAccessException(DBMessages.COULD_NOT_Reserve_Game, e);
		}
		return reserved;
		
	}
	
	/**
	 * Method for finding all boardgames in the database. 
	 * @return
	 * @throws DataAccessException
	 */
	public LinkedList<Boardgame> findAll() throws DataAccessException {
		LinkedList<Boardgame> exists = new LinkedList<Boardgame>();
		exists = bgCon.findAll();
		return exists;
		
	}
	
	public int createSale(Sale currSale) throws DataAccessException {
		int testID = 0;
		testID = sCtr.createSale(currSale);
		return testID;
	}

}
