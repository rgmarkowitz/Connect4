/* Rachel Markowitz
 * 
 * This class represents each piece or circle that fills a Connect 4 board
 * 
 */

import java.awt.*;
import java.io.Serializable;

public class Circle implements Comparable<Circle> {

	private Color color;
	private int xcoord;
	private int ycoord;

	public Circle(Color c, int x, int y) {
		xcoord = x;
		ycoord = y;
		this.color = c;
	}

	public Color getColor() {
		return color;
	}
	
	public int getXcoord() {
		return xcoord;
	}
	
	public int getYcoord() {
		return ycoord;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}

	// draw the circle object
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.xcoord, this.ycoord, 100, 100);
	}
	

	@Override
	public int compareTo(Circle c) {
		if (this.color == c.getColor() && 
				this.xcoord == c.getXcoord() && this.ycoord == c.getYcoord()) {
			return 0;
		} else {
			return -1;
		}
	}
}
	
