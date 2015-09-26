package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Block extends JComponent implements Drawable, Temporal {
	private JFrame Loade_world;
	private Rectangle shape;
	private boolean breakable;
	public JComponent block;
	public double x;
	public double y;
	
	/**
	 * Constructor for the abstract block class.
	 * @param x_pos of the top-left corner of the block to be constructed.
	 * @param y_pos of the top-left corner of the block to be constructed.
	 */
	public Block(int x_pos, int y_pos){
//		this.colour = Color.red;
		this.shape = new Rectangle(x_pos*35, y_pos*35,35,35);
		this.x = x_pos;
		this.y = y_pos;
	}
	
	/**
	 * Checks whether a block can be passed through by character.
	 * @return returns true if the block can be passed through and false if it cannot.
	 */
	public abstract boolean Passable();
	
	/**
	 * Returns a point2D containing the x and y coordinates of the top left corner of the block the function
	 * is called for.
	 *  @return returns a point2D
	 */
	public Point2D getPosition() {
		return new Point2D.Double(this.x, this.y);
	}
	
	/**
	 * This function removes a block from the world.
	 */
	@Override
	public void die() {
		this.Loade_world.remove(this.block);
	}
	
	/**
	 * Gets the color of a block and returns this color.
	 * @return returns a Color
	 */
	@Override
	public abstract Color getColor();
	
	/**
	 * Gets the shape of a block and returns this shape.
	 * @return returns a shape
	 */
	@Override
	public abstract Shape getShape();
	
	/**
	 * Returns true if a block is breakable and false if it cannot be broken.
	 * @return returns true if the block being checked is breakable.
	 */
	@Override
	public abstract boolean breakable();
	
	
	/**
	 * @return returns a string that contains the type of block such as "Air" or "Brick".
	 */
	public abstract String getType();
	
	/**
	 * 
	 * @return the current count
	 */
	public abstract int getCounter();
	
	/**
	 * Increments a counter if the block needs this counter to be incremented.
	 */
	public abstract void incrementCounter();
	
	/**
	 * @return true if the block can be climbed(ladder) and false if it cannot be climbed.
	 */
	public abstract boolean isClimbeable();
	
	/**
	 * @return true if characters can fall through particular blocks.
	 */
	public abstract boolean isFallable();
	
	/**
	 * @return true if a character can hang from a particular block.
	 */
	public abstract boolean isHangable();
	
	/**
	 * Checks if a character is in the same spot
	 * @param hero
	 * @param guard
	 * @return True if the character is indeed in the same spot.
	 */
	public boolean isCharacterInSameSpot(AbstractCharacter hero, AbstractCharacter guard){
		if ((hero.getX() + 10 >= guard.getX() && hero.getX() - 10 <= guard.getX())
				&& (hero.getY() + 10 >= guard.getY() && hero.getY() - 10 <= guard.getY())){
			return true;
		}else{
			return false;
		}
	}
	
	
}
