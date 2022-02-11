package database;

import java.util.LinkedList;

import control.DataAccessException;
import model.Boardgame;

public interface BoardgameDBIF {
	
	LinkedList<Boardgame> searchBoardgames(String name) throws DataAccessException;
	void deleteBoardgames(int gameID) throws DataAccessException;
	Boardgame updateBoardgames(Boardgame boardgame) throws DataAccessException;
	Boardgame insertBoardgame(Boardgame newBoardgame) throws DataAccessException;
	LinkedList<Boardgame> findAll() throws DataAccessException;
	void reduceAmount(int gameID) throws DataAccessException;
}
