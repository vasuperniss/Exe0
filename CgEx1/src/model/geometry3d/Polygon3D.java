package model.geometry3d;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import model.matrixLib.Matrix;
import view.drawing.BaseDrawable;

public class Polygon3D extends BaseDrawable {

	private List<I3DEdge> edges;
	private List<I3DVertex> vertices;
	
	public Polygon3D(List<I3DVertex> vertices) {
		if (vertices.size() < 3)
			throw new RuntimeException("Not enought vertices.");
		
		this.vertices = vertices;
		this.edges = new ArrayList<I3DEdge>();
		int i;
		for (i = 0; i < vertices.size() - 1; i++) {
			this.edges.add(new Edge3D(vertices.get(i), vertices.get(i + 1)));
		}
		this.edges.add(new Edge3D(vertices.get(i), vertices.get(0)));
	}
	
	public List<I3DEdge> getEdges() {
		return this.edges;
	}

	public Polygon3D applyMatrix(Matrix matrix) {
		List<I3DVertex> modified = new ArrayList<I3DVertex>();
		for (I3DVertex v : this.vertices)
			modified.add(v.applyMatrix(matrix));
		return new Polygon3D(modified);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		for (I3DEdge e : this.edges)
			e.draw(g);
	}
	
	@Override
	public void setColor(Color c) {
		super.setColor(c);
		for (I3DEdge e : this.edges)
			e.setColor(c);
	}
}
