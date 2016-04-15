package model.geometry2d;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import view.drawing.BaseDrawable;
import model.geometry3d.I3DEdge;
import model.geometry3d.Polygon3D;

public class Polygon2D extends BaseDrawable {

	private List<I2DEdge> edges;
	
	public Polygon2D(List<I2DVertex> vertices) {
		if (vertices.size() < 2)
			throw new RuntimeException("Not enought vertices.");
		
		this.edges = new ArrayList<I2DEdge>();
		int i;
		for (i = 0; i < vertices.size() - 1; i++) {
			this.edges.add(new Edge2D(vertices.get(i), vertices.get(i + 1)));
		}
		this.edges.add(new Edge2D(vertices.get(i), vertices.get(0)));
	}
	
	public Polygon2D(Polygon3D polygon) {
		this.edges = new ArrayList<I2DEdge>();
		for (I3DEdge e : polygon.getEdges()) {
			this.edges.add(new Edge2D(e));
		}
	}

	public List<I2DEdge> getEdges() {
		return this.edges;
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		for (I2DEdge e : this.edges)
			e.draw(g);
	}
	
	@Override
	public void setColor(Color c) {
		super.setColor(c);
		for (I2DEdge e : this.edges)
			e.setColor(c);
	}
}
