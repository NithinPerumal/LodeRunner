package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Brick extends Block {
	
	private Color brick_color;
	private Rectangle shape;

	public Brick(int x_pos, int y_pos) {
		
		super(x_pos, y_pos);
		this.brick_color = Color.RED;
		this.shape = new Rectangle(x_pos*35, y_pos*35,35,35);

	}

	@Override
	public boolean breakable() {
		return true;
	}

	@Override
	public Color getColor() {
		return this.brick_color;
	}

	@Override
	public boolean Passable() {
		return false;
	}

	@Override
	public Shape getShape() {
		return this.shape;
	}

	@Override
	public String getType() {
		return "Brick";
	}

	@Override
	public int getCounter() {
		return 0;
	}

	@Override
	public void incrementCounter() {
		
	}

	@Override
	public boolean isClimbeable() {
		return false;
	}

	@Override
	public boolean isFallable() {
		return false;
	}

	@Override
	public boolean isHangable() {
		return false;
	}
	
	
}
