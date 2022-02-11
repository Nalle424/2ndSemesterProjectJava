package control;

import java.util.LinkedList;

import database.BoardgameDB;
import database.BoardgameDBIF;
import model.Boardgame;

public class BoardgameCtr {
	
	private BoardgameDBIF bgDB;
	
	public BoardgameCtr() throws DataAccessException {
		bgDB = new BoardgameDB();
	}
	
	/**
	 * Method for searching for boardgames.
	 * @param name
	 * @return
	 * @throws DataAccessException
	 */
	public LinkedList<Boardgame> searchBoardgame (String name) throws DataAccessException {
		LinkedList<Boardgame> exists = null;
		exists = bgDB.searchBoardgames(name);
		return exists;
	}
	
	/**
	 * Method for deleting boardgames from the database.
	 * @param gameID
	 * @throws DataAccessException
	 */
	public void deleteBoardgames(int gameID) throws DataAccessException {
		bgDB.deleteBoardgames(gameID);
	}
	
	/**
	 * Method for updating the information of a boardgame inside the database.
	 * @param boardgame
	 * @return 
	 * @throws DataAccessException
	 */
	public Boardgame updateBoardgames(Boardgame boardgame) throws DataAccessException {
		Boardgame exists = null;
		exists = bgDB.updateBoardgames(boardgame);
		return exists;
	}
	
	/**
	 * Method for creating a new boardgame and inserting it into the database.
	 * @param newBoardgame
	 * @return
	 * @throws DataAccessException
	 */
	public Boardgame insertBoardgame(Boardgame newBoardgame) throws DataAccessException {
		Boardgame bg = bgDB.insertBoardgame(newBoardgame);
		return bg;
	}
	
	/**
	 * Method for finding all boardgames in the database. 
	 * @return
	 * @throws DataAccessException
	 */
	public LinkedList<Boardgame> findAll() throws DataAccessException {
		LinkedList<Boardgame> exists = null;
		exists = bgDB.findAll();
		return exists;
	}

	/**
	 * Method for reducing the amount variable on a boardgame inside the database. 
	 * Used to check if there's more copies of a specific boardgame left to reserve.
	 * @param GameID
	 * @throws DataAccessException
	 */
	public void reduceAmount(int GameID) throws DataAccessException {
		bgDB.reduceAmount(GameID);
		
	}
}
