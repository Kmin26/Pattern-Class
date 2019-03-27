package shape;

import java.awt.Graphics;

public class Ellipse extends Shape{

	@Override
	public void draw(Graphics graphics, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		graphics.drawOval(x, y, width, height);
		
	};

}
