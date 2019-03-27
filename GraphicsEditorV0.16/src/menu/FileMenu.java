package menu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import global.Constants.EFileMenu;
import global.Constants.EMenu;

public class FileMenu extends JMenu {
	
	private JMenuItem newItem;
	
	public FileMenu(String text) {
		super(text);
		
		this.newItem = new JMenuItem(EFileMenu.newItem.getText());
		this.add(this.newItem);
	}
}
