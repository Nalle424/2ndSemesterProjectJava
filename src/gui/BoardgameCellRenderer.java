package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


import model.Boardgame;

 
public class BoardgameCellRenderer implements ListCellRenderer<Boardgame>  {

	private static final long serialVersionUID = 1L;
	private DefaultListCellRenderer dlcr;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Boardgame> list, Boardgame value, int index,
			boolean isSelected, boolean cellHasFocus) {

		
		dlcr = new DefaultListCellRenderer();
		String textToShow = value.getName();
		
		if (value.getAmount() == 0) {
			list.setForeground(new Color(255, 0, 0));
			list.setSelectionForeground(new Color(255, 0, 0)); 
		}
		else {
			list.setForeground(new Color(0, 0, 0));
			list.setSelectionForeground(new Color(0, 0, 0));
		}
		return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
	}


}
