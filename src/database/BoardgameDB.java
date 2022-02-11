package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import control.DataAccessException;
import database.DBMessages;
import model.Boardgame;


public class BoardgameDB implements BoardgameDBIF{
	
	private static final String FIND_ALL_Q = "SELECT * FROM boardgame";
	private PreparedStatement findAllBoardgames;
	
	private static final String FindByName_Q = "SELECT * FROM boardgame WHERE name LIKE ?";
	private PreparedStatement BGName;
	
	private static final String DELETE_BY_GAMEID_Q = "DELETE boardgame WHERE gameID = ?";
	private PreparedStatement deleteBoardgame;
	
	private static final String UPDATE_BOARDGAME_Q = "UPDATE boardgame SET name = ?, amount = ?, description = ?, genre = ?, timeOfPlay = ?, noOfPlayers= ? WHERE gameID = ?";
	private PreparedStatement updateBoardgame;
	
	private static final String INSERT_Q = "INSERT INTO boardgame (name, amount, description, genre, timeOfPlay, noOfPlayers) values (?, ?, ?, ?, ?, ?)";
	private PreparedStatement insertBoardgame;
	
	private static final String REDUCE_A = "UPDATE Boardgame SET amount = amount - 1 where gameID = ?";
	private PreparedStatement reduceAmount;
	
	public BoardgameDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllBoardgames = con.prepareStatement(FIND_ALL_Q);
			BGName = con.prepareStatement(FindByName_Q);
			deleteBoardgame = con.prepareStatement(DELETE_BY_GAMEID_Q);
			updateBoardgame = con.prepareStatement(UPDATE_BOARDGAME_Q);
			insertBoardgame = con.prepareStatement(INSERT_Q);
			reduceAmount = con.prepareStatement(REDUCE_A);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	/**
	 * Method for searching for boardgames.
	 */
	@Override
	public LinkedList<Boardgame> searchBoardgames(String name) throws DataAccessException {
		LinkedList<Boardgame> res = null;
		try {
			BGName.setString(1, "%" + name + "%");
			ResultSet rs = BGName.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}
	
	/**
	 * Method used to create the object, based on the Resultset in the parameter, which is then added to a new LinkedList. 
	 * @param rs
	 * @return
	 * @throws DataAccessException
	 */
	private LinkedList<Boardgame> buildObjects(ResultSet rs) throws DataAccessException {
		LinkedList<Boardgame> res = new LinkedList<>();
		try {
			while (rs.next()) {
				Boardgame currBoardgame = buildObject(rs);
				res.add(currBoardgame);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	
	/**
	 * Method used to build the object based on the Resultset parameter, afterwards it is returned to the previous called method.
	 * @param rs
	 * @return
	 * @throws DataAccessException
	 */
	private Boardgame buildObject(ResultSet rs) throws DataAccessException {
		Boardgame currBoardgame = new Boardgame();
		try {
			currBoardgame.setGameID(rs.getInt("gameID"));
			currBoardgame.setName(rs.getString("name"));
			currBoardgame.setAmount(rs.getInt("amount"));
			currBoardgame.setDescription(rs.getString("description"));
			currBoardgame.setGenre(rs.getString("genre"));
			currBoardgame.setTimeOfPlay(rs.getString("timeOfPlay"));
			currBoardgame.setNoOfPlayers(rs.getString("noOfPlayers"));

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currBoardgame;
	}
	
	/**
	 * Method for deleting a boardgame from the database.
	 */
	public void deleteBoardgames(int gameID) throws DataAccessException {
		try {
			deleteBoardgame.setInt(1, gameID);	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_DELETE_GAME, e);
		}
		try {
			deleteBoardgame.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_DELETE_GAME, e);
		}
	}
	
	/**
	 * Method for updating an existing boardgame within the database.
	 */
	public Boardgame updateBoardgames(Boardgame boardgame) throws DataAccessException {
		try {
			updateBoardgame.setString(1, boardgame.getName());
			updateBoardgame.setInt(2, boardgame.getAmount());
			updateBoardgame.setString(3, boardgame.getDescription());
			updateBoardgame.setString(4, boardgame.getGenre());
			updateBoardgame.setString(5, boardgame.getTimeOfPlay());
			updateBoardgame.setString(6, boardgame.getNoOfPlayers());
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_UPDATE_GAME, e);
		}
		return boardgame;
	}
	
	/**
	 * Method for insert a new boardgame into the database.
	 */
	public Boardgame insertBoardgame(Boardgame newBoardgame) throws DataAccessException {
		try {
			insertBoardgame.setString(1, newBoardgame.getName());
			insertBoardgame.setInt(2, newBoardgame.getAmount());
			insertBoardgame.setString(3, newBoardgame.getDescription());
			insertBoardgame.setString(4, newBoardgame.getGenre());
			insertBoardgame.setString(5, newBoardgame.getTimeOfPlay());
			insertBoardgame.setString(6, newBoardgame.getNoOfPlayers());
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
		}
		try {
			insertBoardgame.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
		}
		return newBoardgame;
	}
	
	/**
	 * Method for returning all of the database existing in the database.
	 */
	public LinkedList<Boardgame> findAll() throws DataAccessException {
		ResultSet rs;
		try {
			rs = this.findAllBoardgames.executeQuery();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_FIND_ALL, e);
		}
		LinkedList<Boardgame> res = buildObjects(rs);
		return res;
	}

	/**
	 * Method for reducing the amount coloumn of a boardgame, when it has been reserved.
	 */
	@Override
	public void reduceAmount(int gameID) throws DataAccessException {

		try {
			reduceAmount.setInt(1, gameID);
			reduceAmount.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_REDUCE_AMOUNT, e);
		}
		
	}
}
