package gui;
import control.DataAccessException;
import control.TableCtr;
import model.GameTable;
import java.util.LinkedList;


public class MainMenu { 


	private static TableCtr TCon;
	private static	int tableNo;
	private static Monitor mon;

	/**
	 * Launch the application.
	 * @throws DataAccessException 
	 */
		public static void main(String[] args) throws DataAccessException {
			mon = new Monitor();
			LinkedList<GameTable> res = new LinkedList<GameTable>();
			res = findTables();
			int i;
			for(i = 0; i < res.size(); i++) {
			tableNo = res.get(i).getTableNo();
			BoardgameReservationUI object = new BoardgameReservationUI(tableNo, mon);
			object.setName("Table " + res.get(i).getTableNo());
			object.setVisible(true);

			
			}
		}

		/**
		 * Method for finding and creatinga list of tables from the database. 
		 * This is used to create a responding number of application windows.
		 * @return
		 * @throws DataAccessException
		 */
	private static LinkedList<GameTable> findTables() throws DataAccessException {
		LinkedList<GameTable> res = new LinkedList<GameTable>();
		TCon = new TableCtr();
		res = TCon.findTables();
		return res;
	}
	
	/**
	 * Monitor class is created here. This is used in the Main function of the MainMenu.
	 * This class has a Wait() and NotifyAll().
	 * @author Heils
	 *
	 */
	public static class Monitor {
		private Monitor() {	
		}
		
		public synchronized void read() throws InterruptedException {
			wait();	
		}
		
		public synchronized void update() {
			notifyAll();
		}
	}

}
