package drawingpannel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import global.Constants.EToolbar;
import shape.Shape;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private MouseHandler mouseHandler;
	private Shape currentTool;
	
	public void setCurrentTool(EToolbar eToolbar) {
		this.currentTool = eToolbar.getShape();
	}
	public DrawingPanel() {
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		this.setBackground(Color.white);
		
		currentTool = EToolbar.rectangle.getShape();
		
	}
	
	private void drawShape() {
		
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(this.getBackground());
		this.currentTool.draw(graphics);
	}	
	private void initDrawing(int x, int y) {
		this.currentTool.setOrigin(x, y);
		this.drawShape();
	}
	private void keepDrawing(int x, int y) {
		// 그림도구를 Graphics라고 하고 이건 운영체제가 가지고 있다. 그래서 운영체제가 쓰는 폰트나 색깔을 그대로 사용 한다. 운영체제가 가진 그림도구를 그대로 내가 받아 와야 한다. 그래서 원래는 MainFrame이 받아서 Drawing Pnael한테 주는 구조이다.
		this.drawShape();
		this.currentTool.setPoint(x, y);
		this.drawShape();
	}
	private void continueDrawing(int x, int y) {
		this.currentTool.addPoint(x, y);
	}
	private void finishDrawing(int x, int y) {
		this.drawShape();
		this.currentTool.setPoint(x, y);
		this.drawShape();
	}
	 

	private class MouseHandler implements MouseListener , MouseMotionListener{
			
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				initDrawing(e.getX(),e.getY());	
			}
			
			else if(e.getClickCount()==2) {
				finishDrawing(e.getX(),e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent e) {
			initDrawing(e.getX(),e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			finishDrawing(e.getX(),e.getY());
		}

		@Override
		public void mouseDragged(MouseEvent e) {	
			keepDrawing(e.getX(),e.getY());
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			keepDrawing(e.getX(),e.getY());
		}
		
	}


}
