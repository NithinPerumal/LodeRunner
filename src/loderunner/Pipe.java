package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Pipe extends Block {
	private Color pipeColor;
	private Rectangle shape;

	public Pipe(int x_pos, int y_pos) {
		super(x_pos, y_pos);
		this.pipeColor = Color.MAGENTA;
		this.shape = new Rectangle(x_pos*35, y_pos*35+10,35,5);

	}

	@Override
	public boolean Passable() {
		return true;
	}
	

	@Override
	public Color getColor() {
		return this.pipeColor;
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
		return "Pipe";
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
		return true;
	}

}
