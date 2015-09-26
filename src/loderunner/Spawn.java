package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Spawn extends Block {

	private Color spawnColor;
	private Rectangle shape;
	
	public Spawn(int x_pos, int y_pos) {
		super(x_pos, y_pos);
		this.spawnColor = Color.BLACK;
		this.shape = new Rectangle(x_pos*35, y_pos*35,35,35);
	}

	@Override
	public boolean Passable() {
		return true;
	}

	@Override
	public Color getColor() {
		return this.spawnColor;
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
		return "Spawn";
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
