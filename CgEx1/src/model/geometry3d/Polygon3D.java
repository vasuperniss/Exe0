package model.geometry3d;

import java.util.ArrayList;
import java.util.List;

public class Polygon3D {

	private List<I3DEdge> edges;
	
	public Polygon3D(List<I3DVertex> vertices) {
		if (vertices.size() < 3)
			throw new RuntimeException("Not enought vertices.");
		
		this.edges = new ArrayList<I3DEdge>();
		for (int i = 0; i < vertices.size() - 1; i++) {
			this.edges.add(new Edge3D(vertices.get(i), vertices.get(i + 1)));
		}
	}
	
	public List<I3DEdge> getEdges() {
		return this.edges;
	}
}
