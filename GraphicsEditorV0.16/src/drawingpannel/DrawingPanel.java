package drawingpannel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import global.Constants.EToolbar;
import shape.Shape;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private enum EActionState {	eReady, e2PDrawing, eNPDrawing	}; // Ŭ������Ŭ��, �������巡�׸�����
	// ������Ʈ ������� �׸� ���׶�� ������ ����. ��� �������� �˷���, �������� �������������� ��Ÿ������ ��. �ΰ����� �Ǻ��ض�.
	// �ϳ��� �׸��� ���, �ϳ��� ���� ����. �׸��� ����� ������ ���� �з��Ѵ�.
	// ����� ������� �� �з��� �Ǿ�� �Ѵ�. �������������� �ذ��� �� �ִ°��� �Ѱ谡 �ִ�.
	// ������ �ܼ��ϰ� ����µ� ��Ұ� �׸��� ����� �ִ�. -> ��� ���� ����Ŀ� �޷�����.
	// �������� ������ �� ������ ������ �׷�����, ������ ������ �巡�׷� �����;� �Ѵ�. -> ���� �����̴� ��?
	// �׸��� ���¸� ������ �з��ؾ� ��. ����� - 2�� / ����- 1��, �������� - 8��, ������Ʈ - 1��. -> ������, �巡��, ������
	// ���.
	// ������ ������ ���¸� ������ �������������� �з�.
	private Vector<Shape> shapeVector;
	private EActionState eActionState;

	private MouseHandler mouseHandler;
	
	private Shape currentTool;
	private Shape currentShape;
	
	public void setCurrentTool(EToolbar currentTool) {
		this.currentTool = currentTool.getShape();
	}

	public DrawingPanel() {
		this.eActionState = EActionState.eReady;
		this.setBackground(Color.WHITE);

		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);

		this.currentTool = EToolbar.rectangle.getShape();
		this.shapeVector = new Vector<Shape>();
	}
	public void paint(Graphics graphics) {
		super.paint(graphics);
		for(Shape shape : this.shapeVector) {
			shape.draw(graphics);
		}
	}

	private void drawShape() {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		this.currentShape.draw(graphics);
	}

	private void initDrawing(int x, int y) {
		this.currentShape = this.currentTool.clone();
		this.currentShape.setOrigin(x, y);
		this.drawShape();
	}

	private void keepDrawing(int x, int y) { // ���콺 �����϶��� �۵�.
		this.drawShape();
		this.currentShape.setPoint(x, y);
		this.drawShape();
	}

	private void continueDrawing(int x, int y) { // �����̴ٰ� �� Ŭ��.
		this.currentShape.addPoint(x, y);

	}

	private void finishDrawing(int x, int y) { // ����Ŭ���ϸ� ��. �׷��� �������� ���������� ���� ����.
		this.shapeVector.add(this.currentShape);
	}

	private class MouseHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}

		public void mouse1Clicked(MouseEvent e) {
			if (eActionState == EActionState.eReady) {
				initDrawing(e.getX(), e.getY());
				eActionState = EActionState.eNPDrawing;
			}else if(eActionState == EActionState.eNPDrawing) {
				continueDrawing(e.getX(), e.getY());
				// ����� ������� ��ǲ�� �ٰ��ΰ� �ϰ� ������ ��ǻ���� ��Ʈ���� �и��� �Ǿ��ִ�.
			}
			// ����� ������� ��ǲ�� �ٰ��ΰ� �ϰ� ������ ��ǻ���� ��Ʈ���� �и��� �Ǿ��ִ�.
		}

		public void mouse2Clicked(MouseEvent e) {
			if (eActionState == EActionState.eNPDrawing) {
				finishDrawing(e.getX(), e.getY());
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
			  if(eActionState == EActionState.eNPDrawing){
				  keepDrawing(e.getX(),e.getY()); 
				  }
			 
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eActionState == EActionState.eReady) {
				initDrawing(e.getX(), e.getY());
				eActionState = EActionState.e2PDrawing;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eActionState == EActionState.e2PDrawing) {
				finishDrawing(e.getX(), e.getY());
				eActionState = EActionState.eReady;
			} // ����� ������� ��ǲ�� �ٰ��ΰ� �ϰ� ������ ��ǻ���� ��Ʈ���� �и��� �Ǿ��ִ�.
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eActionState == EActionState.e2PDrawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
