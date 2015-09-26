package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Gold extends Block {
	
	private Color goldColor;
	private Rectangle shape;
	
	public Gold(int x_pos, int y_pos) {
		super(x_pos, y_pos);
		this.goldColor = Color.YELLOW;
		this.shape = new Rectangle(x_pos*35+7, y_pos*35+15, 20, 20);

	}

	@Override
	public boolean Passable() {
		return true;
	}

	@Override
	public Color getColor() {
		return this.goldColor;
	}

	@Override
	public boolean breakable() {
		return false;
	}

	@Override
	public Shape getShape() {
		return this.shape;
	}

	@Override
	public String getType() {
		return "Gold";
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
		return true;
	}

	@Override
	public boolean isHangable() {
		return false;
	}

}
