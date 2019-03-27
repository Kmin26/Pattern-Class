package menu;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import drawingpannel.DrawingPanel;
import global.Constants.EMenu;

public class Menubar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	//component
	private FileMenu fileMenu;
	
	
	// association
	private DrawingPanel drawingPanel;
	public void associate(DrawingPanel drawingPanel) {
		// TODO Auto-generated method stub
		this.drawingPanel = drawingPanel;
	}

	public Menubar() {
		this.fileMenu = new FileMenu(EMenu.fileMenu.getText());
		this.add(fileMenu);
	}

}
