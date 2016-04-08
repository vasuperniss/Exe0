package model.clipping;

import java.util.List;

import model.geometry2d.I2DEdge;
import model.geometry2d.Vertex2D;

public interface IClipper {
	List<I2DEdge> clipEdges(List<I2DEdge> edges, Vertex2D corner, float width, float height);
}
