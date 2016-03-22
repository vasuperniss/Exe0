package model;

import java.awt.Graphics;
import java.util.List;

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
	public void fillDrawingUsingEdges(Graphics g, List<IEdge> edges);
}
