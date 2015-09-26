package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.geom.Point2D;

/**
 * Represents things that vary with the passing of time.
 * .
 */
public interface Temporal {


	/**
	 * Signals to this object that it's useful life is over.
	 */
	void die();
	
	/**
	 * Signals if the object in question is breakable
	 * @return 
	 */
	boolean breakable();

	/**
	 * Returns the position
	 * @return the position of the block
	 */
	Point2D getPosition();

	String getType();
}

