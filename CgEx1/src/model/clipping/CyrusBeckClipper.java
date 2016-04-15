package model.clipping;

import java.util.Collection;
import java.util.List;

import model.geometry2d.I2DEdge;
import model.geometry2d.Polygon2D;
import model.geometry3d.I3DEdge;

/**
 * The CyrusBeckClipper class. (incomplete - did cohen surtherland algoritm)
 *
 * @author Michael Vassernis 319582888
 */
public class CyrusBeckClipper implements ILineClipper {

	/* (non-Javadoc)
	 * @see model.clipping.ILineClipper#clipEdges(java.util.List)
	 */
	@Override
	public List<I2DEdge> clipEdges(List<I2DEdge> edges) {
		return null;
	}

	/* (non-Javadoc)
	 * @see model.clipping.ILineClipper#clip3DEdgesZeroZ(java.util.List)
	 */
	@Override
	public Collection<I2DEdge> clip3DEdgesZeroZ(List<I3DEdge> edges) {
		return null;
	}

	/* (non-Javadoc)
	 * @see model.clipping.ILineClipper#clipEdge(model.geometry2d.I2DEdge)
	 */
	@Override
	public I2DEdge clipEdge(I2DEdge edge) {
		return null;
	}

	/* (non-Javadoc)
	 * @see model.clipping.ILineClipper#clipPolygon(model.geometry2d.Polygon2D)
	 */
	@Override
	public Polygon2D clipPolygon(Polygon2D polygon) {
		return null;
	}

}
