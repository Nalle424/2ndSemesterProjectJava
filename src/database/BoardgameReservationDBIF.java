package database;

import control.DataAccessException;
import model.Boardgame;

public interface BoardgameReservationDBIF {
	
	boolean reservedBoardgame(int reserveGameID, int cusID, int tableNo) throws DataAccessException;
	boolean reservedBoardgame(int reserveGameID, int tableNo) throws DataAccessException;
}