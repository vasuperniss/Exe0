package model.clipping;

import java.util.Collection;
import java.util.List;

import model.geometry2d.I2DEdge;
import model.geometry2d.Polygon2D;
import model.geometry3d.I3DEdge;

public interface ILineClipper {
	List<I2DEdge> clipEdges(List<I2DEdge> edges);

	Collection<I2DEdge> clip3DEdgesZeroZ(List<I3DEdge> edges);

	I2DEdge clipEdge(I2DEdge edge);

	Polygon2D clipPolygon(Polygon2D polygon);
}
