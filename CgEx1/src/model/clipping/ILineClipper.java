package model.clipping;

import java.util.Collection;
import java.util.List;

import model.geometry2d.I2DEdge;
import model.geometry2d.Polygon2D;
import model.geometry3d.I3DEdge;

/**
 * The ILineClipper interface.
 *
 * @author Michael Vassernis 319582888
 */
public interface ILineClipper {
	
	/**
	 * Clip edges.
	 *
	 * @param edges the edges
	 * @return the list
	 */
	List<I2DEdge> clipEdges(List<I2DEdge> edges);

	/**
	 * Clip3 d edges with zero Z values.
	 *
	 * @param edges the edges
	 * @return the collection of 2d edges
	 */
	Collection<I2DEdge> clip3DEdgesZeroZ(List<I3DEdge> edges);

	/**
	 * Clip edge.
	 *
	 * @param edge the edge
	 * @return the 2d edge
	 */
	I2DEdge clipEdge(I2DEdge edge);

	/**
	 * Clip polygon.
	 *
	 * @param polygon the polygon
	 * @return the polygon 2d
	 */
	Polygon2D clipPolygon(Polygon2D polygon);
}
