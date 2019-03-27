package shape;

import java.awt.Graphics;

public class Line extends Shape {
	
	
		@Override
		public void draw(Graphics graphics, int x, int y, int width, int height) {
			
			graphics.drawLine(x, y, x+width, y+height);
		
	}
}
