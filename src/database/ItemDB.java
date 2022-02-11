package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.Connection;
import control.DataAccessException;
import database.DBMessages;
import model.Item;

public class ItemDB implements ItemDBIF {
	
	private static final String FIND_ITEM_BY_TYPE_Q = "SELECT * FROM item WHERE type = ?";
	private PreparedStatement findItemByType;
	
	private static final String DELETE_BY_ITEMID_Q = "DELETE item WHERE itemID = ?";
	private PreparedStatement deleteItem;
	
	private static final String UPDATE_ITEM_Q = "UPDATE item SET (name, type, price, amount, minAmount) VALUES(?,?,?,?,?,)";
	private PreparedStatement updateItem;
	
	private static final String INSERT_ITEM_Q = "INSERT INTO item (name, type, price, amount, minAmount) values (?,?,?,?,?)";
	private PreparedStatement insertItem;
	
	public ItemDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findItemByType = con.prepareStatement(FIND_ITEM_BY_TYPE_Q);
			deleteItem = con.prepareStatement(DELETE_BY_ITEMID_Q);
			updateItem = con.prepareStatement(UPDATE_ITEM_Q);
			insertItem = con.prepareStatement(INSERT_ITEM_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	/**
	 * Method for building objects based on the Resultset parameter, which is then added to a LinkedList.
	 * @param rs
	 * @return
	 * @throws DataAccessException
	 */
	private LinkedList<Item> buildObjects(ResultSet rs) throws DataAccessException {
		LinkedList<Item> res = new LinkedList<>();
		try {
			while (rs.next()) {
				Item currItem = buildObject(rs);
				res.add(currItem);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	
	/**
	 * Method for building an object based on the Resultset parameter, which is then returned to the previous method.
	 * @param rs
	 * @return
	 * @throws DataAccessException
	 */
	private Item buildObject(ResultSet rs) throws DataAccessException {
		Item currItem = new Item();
		try {
			currItem.setItemID(rs.getInt("ItemID"));
			currItem.setName(rs.getString("name"));
			currItem.setType(rs.getString("type"));
			currItem.setPrice(rs.getDouble("Price"));
			currItem.setAmount(rs.getInt("amount"));
			currItem.setMinAmount(rs.getInt("minAmount"));

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currItem;
	}
	
	/**
	 * Method for searching for a specific item type within the database.
	 */
	@Override
	public LinkedList<Item> searchItems(String type) throws DataAccessException {
		LinkedList<Item> res = null;
		try {
			findItemByType.setString(1, type);
			ResultSet rs = findItemByType.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}
	
	/**
	 * Method for deleting a specific item within the database.
	 */
	public void deleteItems(int itemID) throws DataAccessException {
		try {
			deleteItem.setInt(1, itemID);	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_DELETE_ITEM, e);
		}
		try {
			deleteItem.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_DELETE_ITEM, e);
		}
	}
	
	/**
	 * Method for updating a specific item within the database.
	 */
	public Item updateItem(Item item) throws DataAccessException {
		try {
			updateItem.setString(1, item.getName());
			updateItem.setString(2, item.getType());
			updateItem.setDouble(3, item.getPrice());
			updateItem.setInt(4, item.getAmount());
			updateItem.setInt(5, item.getMinAmount());
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_UPDATE_ITEM, e);
		}
		return item;
	}
	
	/**
	 * Method for inserting a new item into the database.
	 */
	public Item insertItem(Item newItem) throws DataAccessException {
		try {
			insertItem.setString(1, newItem.getName());
			insertItem.setString(2, newItem.getType());
			insertItem.setDouble(3, newItem.getPrice());
			insertItem.setInt(4, newItem.getAmount());
			insertItem.setInt(4, newItem.getMinAmount());
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_INSERT_ITEM, e);
		}
		try {
			insertItem.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_INSERT_ITEM, e);
		}
		return newItem;
	}	
}