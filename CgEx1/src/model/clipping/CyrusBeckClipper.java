package model.clipping;

import java.util.Collection;
import java.util.List;

import model.geometry2d.I2DEdge;
import model.geometry2d.Polygon2D;
import model.geometry3d.I3DEdge;

public class CyrusBeckClipper implements ILineClipper {

	@Override
	public List<I2DEdge> clipEdges(List<I2DEdge> edges) {
		return null;
	}

	@Override
	public Collection<I2DEdge> clip3DEdgesZeroZ(List<I3DEdge> edges) {
		return null;
	}

	@Override
	public I2DEdge clipEdge(I2DEdge edge) {
		return null;
	}

	@Override
	public Polygon2D clipPolygon(Polygon2D polygon) {
		return null;
	}

}
