package control;

import java.util.LinkedList;
import database.ItemDBIF;
import database.ItemDB;
import model.Item;

public class ItemCtr {
	
	private ItemDBIF iDB;
	
	public ItemCtr() throws DataAccessException {
		iDB = new ItemDB();
	}
	
	/**
	 * Method used to select between food and drinks.
	 * @param name
	 * @return
	 * @throws DataAccessException
	 */
	public LinkedList<Item> searchItems(String name) throws DataAccessException {
		LinkedList<Item> exists = null;
		exists = iDB.searchItems(name);
		return exists;
	}
	
	/**
	 * Method for deleting an item.
	 * @param itemID
	 * @throws DataAccessException
	 */
	public void deleteItems(int itemID) throws DataAccessException {
		iDB.deleteItems(itemID);
	}
	
	/**
	 * Method used for updating an item inside the database.
	 * @param item
	 * @return
	 * @throws DataAccessException
	 */
	public Item updateItem(Item item) throws DataAccessException {
		Item exists = null;
		exists = iDB.updateItem(item);
		return exists;
	}
	
	/**
	 * Method for inserting a new item into the database.
	 * @param newItem
	 * @return
	 * @throws DataAccessException
	 */
	public Item insertItem(Item newItem) throws DataAccessException {
		Item item = iDB.insertItem(newItem);
		return item;
	}
}
