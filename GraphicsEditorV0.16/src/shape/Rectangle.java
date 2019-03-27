package shape;

import java.awt.Graphics;

public class Rectangle extends Shape {
	
	
		@Override
		public void draw(Graphics graphics) {
			// TODO Auto-generated method stub
			graphics.drawRect(x1, y1, x2-x1, y2-y1);
		
	}
}
