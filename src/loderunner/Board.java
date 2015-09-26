package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Board extends Block {
	private Color boardColor;
	private Rectangle shape;


	public Board(int x_pos, int y_pos) {
		super(x_pos, y_pos);
		this.boardColor = Color.DARK_GRAY;
		this.shape = new Rectangle(x_pos*35, y_pos*35,35,35);
	}

	@Override
	public boolean Passable() {
		return false;
	}

	@Override
	public Color getColor() {
		return this.boardColor;
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
		return "Board";
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

	@Override
	public boolean isCharacterInSameSpot(AbstractCharacter hero,
			AbstractCharacter guard) {
		return false;
	}

}
