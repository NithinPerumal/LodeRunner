package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class DeadGuard extends Block {
	private Color deadColor;
	private Rectangle shape;
	private int counter;
	
	public DeadGuard(int x_pos, int y_pos, int count) {
		super(x_pos, y_pos);
		this.deadColor = Color.BLUE;
		this.shape = new Rectangle(x_pos*35, y_pos*35,35,35);
		this.counter = count;
	}

	@Override
	public boolean Passable() {
		return false;
	}

	@Override
	public Color getColor() {
		return this.deadColor;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return this.shape;
	}

	@Override
	public boolean breakable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "DeadGuard";
	}

	@Override
	public int getCounter() {
		// TODO Auto-generated method stub
		return counter;
	}

	@Override
	public void incrementCounter() {
		// TODO Auto-generated method stub
		counter++;
	}

	@Override
	public boolean isClimbeable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFallable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHangable() {
		// TODO Auto-generated method stub
		return false;
	}

}
