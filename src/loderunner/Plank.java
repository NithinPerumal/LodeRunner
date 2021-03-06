package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Plank extends Block {

	private Color plankColor;
	private Rectangle shape;
	
	public Plank(int x_pos, int y_pos) {
		super(x_pos, y_pos);
		this.plankColor = new Color(101,67,33); 
		this.shape = new Rectangle(x_pos*35, y_pos*35, 35, 10);
	}

	@Override
	public boolean Passable() {
		return false;
	}

	@Override
	public Color getColor() {
		return this.plankColor;
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
		return "Plank";
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
