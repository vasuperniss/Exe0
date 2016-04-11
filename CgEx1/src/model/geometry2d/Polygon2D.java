package model.geometry2d;

import java.util.ArrayList;
import java.util.List;

public class Polygon2D {

	private List<I2DEdge> edges;
	
	public Polygon2D(List<I2DVertex> vertices) {
		if (vertices.size() < 3)
			throw new RuntimeException("Not enought vertices.");
		
		this.edges = new ArrayList<I2DEdge>();
		for (int i = 0; i < vertices.size() - 1; i++) {
			this.edges.add(new Edge2D(vertices.get(i), vertices.get(i + 1)));
		}
	}
	
	public List<I2DEdge> getEdges() {
		return this.edges;
	}
}
