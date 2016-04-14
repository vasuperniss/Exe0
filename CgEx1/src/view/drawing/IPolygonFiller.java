package view.drawing;

import java.awt.Color;
import java.util.List;

import model.geometry2d.I2DEdge;
import model.geometry3d.I3DEdge;

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
	public List<I2DEdge> fillDrawingUsingEdges(List<I2DEdge> edges, Color color);
	
	public List<I2DEdge> fillDrawingUsing3DEdgesZeroZ(List<I3DEdge> edges, Color color);
}
