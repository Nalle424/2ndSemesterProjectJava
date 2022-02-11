package database;

import java.sql.SQLException;
import java.util.LinkedList;

import control.DataAccessException;
import model.GameTable;

public interface TableDBIF {

	LinkedList<GameTable> findTables() throws DataAccessException;

}
