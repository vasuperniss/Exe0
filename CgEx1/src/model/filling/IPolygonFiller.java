package model.filling;

import java.awt.Color;
import java.util.List;

import model.geometry2d.I2DEdge;
import model.geometry3d.I3DEdge;

/**
 * The Interface IPolygonFiller.
 * 
 * @author Michael Vassernis 319582888
 */
public interface IPolygonFiller {

	/**
	 * Fill drawing using edges onto g.
	 *
	 * @param edges the edges
	 * @param color the color
	 * @return the list of edges
	 */
	public List<I2DEdge> fillDrawingUsingEdges(List<I2DEdge> edges, Color color);
	
	/**
	 * Fill drawing using 3d edges with zero Z values.
	 *
	 * @param edges the edges
	 * @param color the color
	 * @return the list of edges
	 */
	public List<I2DEdge> fillDrawingUsing3DEdgesZeroZ(List<I3DEdge> edges, Color color);
}
