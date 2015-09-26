package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Broken extends Block {

	private Color brokenColor;
	private Rectangle shape;
	private int counter;
	
	public Broken(int x_pos, int y_pos) {
		super(x_pos, y_pos);
		this.brokenColor = Color.BLACK;
		this.shape = new Rectangle(x_pos*35, y_pos*35,35,35);
		this.counter = 0;
	}

	@Override
	public boolean Passable() {
		return true;
	}

	@Override
	public Color getColor() {
		return this.brokenColor;
	}

	@Override
	public Shape getShape() {
		return this.shape;
	}

	@Override
	public boolean breakable() {
		return false;
	}

	@Override
	public String getType() {
		return "Broken";
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public void incrementCounter() {
		counter++;
	}

	@Override
	public boolean isClimbeable() {
		return false;
	}

	@Override
	public boolean isFallable() {
		return true;
	}

	@Override
	public boolean isHangable() {
		return false;
	}
}
