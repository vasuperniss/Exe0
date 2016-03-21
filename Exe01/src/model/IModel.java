package model;

import java.awt.Graphics;

/**
 * The Interface IModel.
 */
public interface IModel {

	/**
	 * Draws the Model onto g.
	 *
	 * @param g the Graphics object to draw on
	 */
	public void draw(Graphics g);

	/**
	 * Sets the polygon filling flag.
	 *
	 * @param filled - true to fill the polygons, else false
	 */
	public void setFilled(boolean filled);

	/**
	 * Adds the temporary edge to the Model.
	 *
	 * @param x1 the x of the first vertex
	 * @param y1 the y of the first vertex
	 * @param x2 the x of the second vertex
	 * @param y2 the y of the second vertex
	 */
	public void addTempEdge(int x1, int y1, int x2, int y2);

	/**
	 * The Polygon is finalized.
	 * Adds the temporary edges to the main Edges of the scene.
	 */
	public void addTempEdgesToScene();
}
