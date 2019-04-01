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

	private enum EActionState {	eReady, e2PDrawing, eNPDrawing	}; // 클릭무브클릭, 프레스드래그릴리즈
	// 투포인트 드로잉은 네모 동그라미 라인을 가짐. 어떻게 도형인지 알려면, 도구에서 폴리모피즘으로 배타적으로 뜯어냄. 두가지로 판별해라.
	// 하나는 그리는 방식, 하나는 실제 도형. 그리는 방식이 같은것 끼리 분류한다.
	// 무브와 드로잉은 또 분류가 되어야 한다. 폴리모피즘으로 해결할 수 있는것이 한계가 있다.
	// 도형을 단순하게 만드는데 요소가 그리는 방식이 있다. -> 몇개의 점을 찍느냐에 달려있음.
	// 프레스를 눌렀을 때 도형이 없으면 그려지고, 도형이 있으면 드래그로 끌려와야 한다. -> 도형 움직이는 거?
	// 그리는 상태를 가지고 분류해야 함. 드로잉 - 2개 / 무빙- 1개, 리사이즈 - 8개, 로테이트 - 1개. -> 프레스, 드래그, 릴리즈
	// 사용.
	// 도형의 종류와 상태를 가지고 폴리모피즘으로 분류.
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

	private void keepDrawing(int x, int y) { // 마우스 움직일때만 작동.
		this.drawShape();
		this.currentShape.setPoint(x, y);
		this.drawShape();
	}

	private void continueDrawing(int x, int y) { // 움직이다가 점 클릭.
		this.currentShape.addPoint(x, y);

	}

	private void finishDrawing(int x, int y) { // 더블클릭하면 끝. 그러나 폴리곤은 마지막점이 원점 연결.
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
				// 사람이 어떤식으로 인풋을 줄것인가 하고 실제로 컴퓨터의 컨트롤이 분리가 되어있다.
			}
			// 사람이 어떤식으로 인풋을 줄것인가 하고 실제로 컴퓨터의 컨트롤이 분리가 되어있다.
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
			} // 사람이 어떤식으로 인풋을 줄것인가 하고 실제로 컴퓨터의 컨트롤이 분리가 되어있다.
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
