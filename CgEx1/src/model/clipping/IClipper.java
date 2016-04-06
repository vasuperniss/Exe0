package model.clipping;

import java.util.List;

import model.geometry.IEdge;
import model.geometry.Vertex2D;

public interface IClipper {
	List<IEdge> clipEdges(List<IEdge> edges, Vertex2D corner, float width, float height);
}
