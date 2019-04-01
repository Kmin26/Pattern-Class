package shape;

import java.awt.Graphics;

public class Polygon extends Shape{
	private int[] xPoints;
	private int[] yPoints;
	private int nPoints;
	private final static int nMaxPoint =20;
	
	public Polygon() {
		this.nPoints = 0;
		this.xPoints = new int[nMaxPoint];
		this.yPoints = new int[nMaxPoint];
		
	}
	@Override
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.drawPolygon(xPoints,yPoints,nPoints);
	}

	@Override
	public void setOrigin(int x, int y) {
		
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;
		this.nPoints = this.nPoints+1;
		
		
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;
		this.nPoints = this.nPoints+1;		
		
	}
	
	public void setPoint(int x, int y) {
		this.xPoints[nPoints-1] = x;
		this.yPoints[nPoints-1] = y;
	}
	

	public void addPoint(int x, int y) {
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;
		this.nPoints = this.nPoints+1;
	}
	
	public void checkMaxPoint() {
		
	}

}
