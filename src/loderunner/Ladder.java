package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Ladder extends Block {
	
	private Color ladderColor;
	private Rectangle shape;
	
	public Ladder(int x_pos, int y_pos) {
		super(x_pos, y_pos);
		this.ladderColor = Color.CYAN;
		this.shape = new Rectangle(x_pos*35, y_pos*35,35,35);

	}

	@Override
	public Color getColor() {
		return this.ladderColor;
	}

	@Override
	public boolean breakable() {
		return false;
	}

	@Override
	public boolean Passable() {
		return true;
	}

	@Override
	public Shape getShape() {
		return this.shape;
	}

	@Override
	public String getType() {
		return "Ladder";
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
		return true;
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
