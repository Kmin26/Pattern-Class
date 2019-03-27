package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import drawingpannel.DrawingPanel;
import global.Constants.EMainFrame;
import menu.Menubar;
import toolbar.ToolBar;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//Components 
	private Menubar menubar;
	private ToolBar toolBar;
	private DrawingPanel drawingPanel;

	public MainFrame() {
		// attribute : �Ӽ� �� �� �꿡 ���� ����
		
		this.setLocation(EMainFrame.x.getValue(),EMainFrame.y.getValue());
		this.setSize(EMainFrame.w.getValue(), EMainFrame.h.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// component : �ش��� ��ǰ.
		this.menubar = new Menubar();
		this.setJMenuBar(menubar);

		this.setLayout(new BorderLayout());

		this.toolBar = new ToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);

		this.drawingPanel = new DrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
		
		//association
		this.toolBar.associate(this.drawingPanel);
		this.menubar.associate(this.drawingPanel);
		
	}




}
