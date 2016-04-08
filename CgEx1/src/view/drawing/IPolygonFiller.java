package view.drawing;

import java.awt.Graphics;
import java.util.List;

import model.geometry2d.I2DEdge;

/**
 * The Interface IPolygonFiller.
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public interface IPolygonFiller {

	/**
	 * Fill drawing using edges onto g.
	 *
	 * @param g the Graphics object to draw on
	 * @param edges the edges
	 */
	public void fillDrawingUsingEdges(Graphics g, List<I2DEdge> edges);
}
