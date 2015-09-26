package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Air extends Block {
	
	private Color airColor;
	private Rectangle shape;
	
	public Air(int x_pos, int y_pos) {
		super(x_pos, y_pos);
		// TODO Auto-generated constructor stub
		this.airColor = Color.BLACK;
		this.shape = new Rectangle(x_pos*35, y_pos*35,35,35);

	}

	@Override
	public boolean Passable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.airColor;
	}

	@Override
	public boolean breakable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return this.shape;
	}

	@Override
	public String getType() {
		return "Air";
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
