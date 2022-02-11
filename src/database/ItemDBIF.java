package database;

import java.util.LinkedList;

import control.DataAccessException;
import model.Item;

public interface ItemDBIF {
	
	LinkedList<Item> searchItems(String name) throws DataAccessException;
	void deleteItems(int itemID) throws DataAccessException;
	Item updateItem(Item item) throws DataAccessException;
	Item insertItem(Item newItem) throws DataAccessException;
}
