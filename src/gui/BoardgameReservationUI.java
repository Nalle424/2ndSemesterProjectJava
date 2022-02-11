package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import control.BoardgameReservationCtr;
import control.DataAccessException;
import control.ItemCtr;
import control.SaleCtr;
import gui.MainMenu.Monitor;
import model.Boardgame;
import model.Item;
import model.Sale;
import model.SaleLine;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class BoardgameReservationUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private BoardgameReservationCtr bgCon;
	private JList<Boardgame> boardgameList;
	private int tableNo;
	private JTextPane descriptionText;
	private UpdateThread uThread;
	private JTextField amountTxt;
	private JTable itemTable;
	private JList<Item> itemList;
	private ItemCtr iCtr;
	private DefaultTableModel tModel;
	private Object[] dataRows;
	private Sale currSale;
	private SaleCtr sCtr;
	private JTextPane noteTxt;
	private JLabel lblOrderBestilt;


	/**
	 * Create the frame.
	 * @param i 
	 * @param mon 
	 */
	public BoardgameReservationUI(int i, Monitor mon) {
		tableNo = i;
		setTitle("Table "  + tableNo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Hj\u00E6lp");
		btnNewButton.setBounds(525, 0, 89, 22);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 614, 346);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 140, 0));
		tabbedPane.addTab("Br\u00E6tspil", null, panel_1, null);
		panel_1.setLayout(null);
		
		
		
		JComboBox comboCat = new JComboBox();
		comboCat.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboCat.addItem("Category");
		comboCat.addItem("Co-op");	
		comboCat.addItem("Strategy");
		comboCat.addItem("Competitive");
		comboCat.addItem("Adventure");
		comboCat.addItem("Horror");
		comboCat.addItem("Fantasy");
		comboCat.setBounds(10, 13, 315, 34);
		panel_1.add(comboCat);
		
				
				boardgameList = new JList<>();
				boardgameList.setBackground(Color.WHITE);
				boardgameList.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						hasFocus();
					}
				});
				boardgameList.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				boardgameList.setBounds(10, 146, 315, 161);
				panel_1.add(boardgameList);
				
				JButton btnNewButton_1 = new JButton("Bestil");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							reservedBoardgame();
						} catch (DataAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							complain("Data error", "type a name", e1);
						}
					}
				});
				btnNewButton_1.setBounds(498, 273, 89, 34);
				panel_1.add(btnNewButton_1);
				
				JComboBox comboPlayer = new JComboBox();
				comboPlayer.setMaximumRowCount(9);
				comboPlayer.addItem("Amount of players");
				comboPlayer.addItem("1 players");
				comboPlayer.addItem("2 players");
				comboPlayer.addItem("3 players");
				comboPlayer.addItem("4 players");
				comboPlayer.addItem("5 players");
				comboPlayer.addItem("6 players");
				comboPlayer.addItem("7 players");
				comboPlayer.addItem("8 players");
				comboPlayer.setBounds(10, 56, 315, 34);
				panel_1.add(comboPlayer);
				
				JComboBox comboTime = new JComboBox();
				comboTime.setMaximumRowCount(15);
				comboTime.addItem("Amount of time");
				comboTime.addItem("10 minutes");
				comboTime.addItem("20 minutes");
				comboTime.addItem("30 minutes");
				comboTime.addItem("40 minutes");
				comboTime.addItem("50 minutes");
				comboTime.addItem("60 minutes");
				comboTime.addItem("70 minutes");
				comboTime.addItem("80 minutes");
				comboTime.addItem("90 minutes");
				comboTime.setBounds(10, 101, 315, 34);
				panel_1.add(comboTime);
				
				JButton btnClearFields = new JButton("Clear fields");
				btnClearFields.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clearFields();
					}
				});
				btnClearFields.setBounds(345, 273, 101, 34);
				panel_1.add(btnClearFields);
				
				txtSearch = new JTextField();
				txtSearch.addKeyListener(new KeyAdapter() {
					@Override
					//gør det muligt at sørge efter hver tastatur tryk
					public void keyReleased(KeyEvent e) {
						try {
							searchBoardgames();
						} catch (DataAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				txtSearch.setText("Search games");
				txtSearch.setBounds(335, 13, 264, 34);
				panel_1.add(txtSearch);
				txtSearch.setColumns(10);
				
				descriptionText = new JTextPane();
				descriptionText.setBackground(Color.WHITE);
				descriptionText.setEditable(false);
				descriptionText.setBounds(335, 56, 264, 206);
				panel_1.add(descriptionText);
				
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		tabbedPane.addTab("Mad og drikke", null, panel, null);
		panel.setLayout(null);
		
		JButton btnMad = new JButton("Mad");
		btnMad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = "Mad";
				try {
					searchItems(type);
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMad.setBounds(10, 9, 89, 23);
		panel.add(btnMad);
		
		JButton btnDrikke = new JButton("Drikke");
		btnDrikke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = "Drikke";
				try {
					searchItems(type);
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDrikke.setBounds(109, 9, 89, 23);
		panel.add(btnDrikke);
		
		JLabel lblKurv = new JLabel("Kurv");
		lblKurv.setBounds(434, 13, 48, 14);
		panel.add(lblKurv);
		
		JButton btnBestil = new JButton("Bestil");
		btnBestil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					placeSale();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBestil.setBounds(510, 284, 89, 23);
		panel.add(btnBestil);
		
		JButton btnAnnule = new JButton("Annuller");
		btnAnnule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSale();
			}
		});
		btnAnnule.setBounds(411, 284, 89, 23);
		panel.add(btnAnnule);
		
		JButton btnTilfjTilKurv = new JButton("Tilf\u00F8j til kurv");
		btnTilfjTilKurv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCart();
			}
		});
		btnTilfjTilKurv.setBounds(223, 143, 89, 23);
		panel.add(btnTilfjTilKurv);
		
		amountTxt = new JTextField();
		amountTxt.setBounds(221, 112, 96, 20);
		panel.add(amountTxt);
		amountTxt.setColumns(10);
		amountTxt.setText("1");
		
		JLabel lblAntal = new JLabel("Antal");
		lblAntal.setBounds(238, 87, 48, 14);
		panel.add(lblAntal);
		
		tModel = new DefaultTableModel();
		Object[] columnNames = { "Produkt", "Antal", "Pris" };
		tModel.setColumnIdentifiers(columnNames);
		
		
		itemList = new JList<Item>();
		itemList.setBounds(10, 43, 188, 264);
		panel.add(itemList);
		itemTable = new JTable();
		itemTable.setBounds(344, 44, 233, 229);
		itemTable.getTableHeader().setReorderingAllowed(false);
		itemTable.setEnabled(false);
		itemTable.setModel(tModel);
		itemTable.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 38, 266, 141);
		panel.add(scrollPane);
		scrollPane.setViewportView(itemTable);
		
		noteTxt = new JTextPane();
		noteTxt.setBounds(333, 190, 266, 84);
		panel.add(noteTxt);
		
		lblOrderBestilt = new JLabel("Order bestilt!");
		lblOrderBestilt.setBounds(223, 221, 75, 23);
		panel.add(lblOrderBestilt);
		
		init();
		uThread = new UpdateThread(mon, this);
		uThread.start();
		
	}
	/**
	 * Method for initializing Controllers, as well as setting current Sale object.
	 */
	private void init() {
		try {
			bgCon = new BoardgameReservationCtr();
			iCtr = new ItemCtr();
			sCtr = new SaleCtr();
			currSale = new Sale();
			currSale.setTableNo(this.tableNo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			complain("Data store error", "Could not establish connection to the data storage", e);
		}
		BoardgameCellRenderer bgcr = new BoardgameCellRenderer();
		boardgameList.setCellRenderer(bgcr);
		
		ItemCellRenderer icr = new ItemCellRenderer();
		itemList.setCellRenderer(icr);	
		try {
			searchBoardgames();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for adding product to Current Sale object.
	 */
	public void addToCart() {
		saleOrderedFalse();
		int amount = Integer.parseInt(amountTxt.getText());
		Item item = new Item();
		item = itemList.getSelectedValue();
		SaleLine sl = new SaleLine(item.getItemID(), amount);
		currSale.addSaleLine(sl);
		
		dataRows = new Object[3];
		dataRows[0] = item.getName();
		dataRows[1] = amount;
		dataRows[2] = item.getPrice();
		
		tModel.addRow(dataRows);
	}
	
	/**
	 * Method for searching for boardgames.
	 * @throws DataAccessException
	 */
	public void searchBoardgames() throws DataAccessException {
		LinkedList<Boardgame> bg = bgCon.searchBoardgame(txtSearch.getText());
		if (!bg.isEmpty()) {
			showListOfGames(bg);
		}
		else if (txtSearch.getText().contains("Search games")) {
			showListOfGames(bgCon.findAll());
		}	else	{
			bg.clear();
			showListOfGames(bg);
		}		
	}
	
	/**
	 * Method for adding a the current sale to the table.
	 * @throws DataAccessException
	 */
	public void placeSale() throws DataAccessException {
		currSale.setNote(noteTxt.getText());
		bgCon.createSale(currSale);
		clearSale();
		saleOrderedTrue();
	}
	
	/**
	 * Method for resetting the UI of ordering food and drinks.
	 */
	public void clearSale() {
		tModel.setRowCount(0);
		currSale.clear(currSale);
	}
	
	/**
	 * method for setting visibility of label of saleOrdered to false.
	 */
	public void saleOrderedFalse() {
		lblOrderBestilt.setVisible(false);
	}
		
	/**
	 * method for setting visibility of label of saleOrdered to true.
	 */
	public void saleOrderedTrue() {
		lblOrderBestilt.setVisible(true);
	}
	
	/**
	 * Method for setting the description text for the current selected game.
	 * @return
	 */
	public boolean hasFocus() {
		descriptionText.setText(boardgameList.getSelectedValue().getDescription());
		return rootPaneCheckingEnabled;
	}
	
	/**
	 * Method for creating a list of games, based on the data in the LinkedList from parameter.
	 * @param bg
	 */
	private void showListOfGames(LinkedList<Boardgame> bg) {
		DefaultListModel<Boardgame> model = new DefaultListModel<>();
		for (int i = 0; i < bg.size(); i++) {
			model.addElement(bg.get(i));
		}
		boardgameList.setModel(model);
	}
	
	/**
	 * Method for switching between Food or Drinks type of items.
	 * @param type
	 * @throws DataAccessException
	 */
	public void searchItems(String type) throws DataAccessException {
		saleOrderedFalse();
		LinkedList<Item> iList = new LinkedList<Item>();
		iList = iCtr.searchItems(type);
		showListOfItems(iList);
		
	}
	
	/**
	 * Method for creating a list of items, based on the LinkedList from parameter.
	 * @param item
	 */
	private void showListOfItems(LinkedList<Item> item) {
		DefaultListModel<Item> model = new DefaultListModel<>();
		for (int i = 0; i < item.size(); i++) {
			model.addElement(item.get(i));
		}
		itemList.setModel(model);
	}

	/**
	 * Method for reserving the currently selected boardgame, as well as calling thread Update to notifyAll threads.
	 * @return null
	 * @throws DataAccessException
	 */
	public Boardgame reservedBoardgame() throws DataAccessException {
		int cusID = 0;
		Boardgame bg = boardgameList.getSelectedValue();
		bgCon.reservedBoardgame(bg.getGameID(), cusID, tableNo);
		try {
			uThread.update();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	/** 
	 * Method for complain text used in try and catch.
	 * @param title
	 * @param text
	 * @param e
	 */
	private void complain(String title, String text, Exception e) {
		JOptionPane.showMessageDialog(this, text + " (" + e.getMessage() + ") ", title, JOptionPane.OK_OPTION);
	}
	
	/**
	 * Method for clearing the search line
	 */
	public void clearFields() {
		txtSearch.setText("");
	}
	



/**
 * Thread creation is here.
 * @author Heils
 *
 */
class UpdateThread extends Thread {
	private Monitor mon;
	private BoardgameReservationUI bgUI;
	
	/**
	 * Adding Monitor as well as BoardgameReservationUI to the thread,
	 * making it possible to call their methods.
	 * @param mon
	 * @param bgUI
	 */
	public UpdateThread(Monitor mon, BoardgameReservationUI bgUI) {
		this.mon = mon;
		this.bgUI = bgUI;
	}
	
	/**
	 * Method for calling monitors Update method, which has a NotifyAll.
	 * @throws InterruptedException
	 */
	public void update() throws InterruptedException {
		mon.update();
	}
	
	/**
	 * Run method for the thread. This is called when threads ".start" is called.
	 * In it, monitors Read method is called, which has a Wait function.
	 */
	public void run() {
		try {
			while(true) {
				mon.read();
				bgUI.searchBoardgames();
			}
		} catch (InterruptedException | DataAccessException e) {
			e.printStackTrace();
		}
	}
}
}




