package toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JToolBar;

import drawingpannel.DrawingPanel;
import global.Constants.EToolbar;



public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	//component
	private Vector<JButton> buttons;
	
	//association
	private DrawingPanel drawingPanel; 
	public void associate(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	public ToolBar() {
		this.buttons = new Vector<JButton>();
		ActionListener actionhandler = new ActionHandler();
		
		for(EToolbar eToolbar: EToolbar.values()) {
			JButton button = new JButton(eToolbar.getText());
			button.addActionListener(actionhandler);
			button.setActionCommand(eToolbar.name());
			this.buttons.add(button);
			this.add(button);
		}	
		
	}
	
	class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
				drawingPanel.setCurrentTool(EToolbar.valueOf(event.getActionCommand()));// 몇번째 enumeration인지 꺼내서 
				
		}
	}




}
