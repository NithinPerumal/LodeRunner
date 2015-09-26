package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

public abstract class AbstractCharacter {
	private double x;
	private double y;
	private Rectangle shape;
	private Block[][] blockList;
	private boolean isFalling;
	
	public AbstractCharacter(int x_pos, int y_pos, Block[][] blocks){
		this.x = x_pos;
		this.y = y_pos;
		this.shape = new Rectangle(0, 0, 0, 0);
		this.blockList = blocks;
		this.isFalling = false;
	}
	
	/**
	 * Returns the Color of the character object in question
	 * @return Color
	 */
	public abstract Color getColor();
	
	/**
	 * Sets the color of the character block
	 * @param newColor
	 */
	public abstract void setColor(Color newColor);
	
	/**
	 * 
	 * @return Returns the character block to the user
	 */
	public abstract Shape getShape();
	
	/**
	 * Is used to update the position of the hero and helps update the current position of the guard.
	 * @param deltaX - The x displacement
	 * @param deltaY - The y displacement
	 */
	public abstract void updatePosition(int deltaX, int deltaY);
	
	/**
	 * Checks whether a character object is falling and returns true if it is.
	 * @return - returns true if the character object is falling.
	 */
	public abstract boolean checkFall();
	
	/**
	 * Checks if a character object is dead or not.
	 * @return - returns true if the character object is dead.
	 */
	public abstract boolean die();
	
	/**
	 * Gets the x-coordinate of the top-left corner of the character object that calls it.
	 * @return - x-coordinate of the top-left corner.
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Gets the y-coordinate of the top-left corner of the character object that calls it.
	 * @return - y-coordinate of the top-left corner.
	 */
	public double getY() {
		return this.y;
	}
	
	
}
