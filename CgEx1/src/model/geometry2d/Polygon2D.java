package model.geometry2d;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import view.drawing.BaseDrawable;
import model.geometry3d.I3DEdge;
import model.geometry3d.Polygon3D;

/**
 * The Class Polygon2D.
 * 
 * @author Michael Vassernis 319582888
 */
public class Polygon2D extends BaseDrawable {

	/** The edges of the polygon. */
	private List<I2DEdge> edges;
	
	/**
	 * Instantiates a new polygon 2d using a list of vertices.
	 *
	 * @param vertices the vertices
	 */
	public Polygon2D(List<I2DVertex> vertices) {
		if (vertices.size() < 2)
			throw new RuntimeException("Not enought vertices.");
		
		this.edges = new ArrayList<I2DEdge>();
		int i;
		// creates the polygon edges based on the order of the vertices
		for (i = 0; i < vertices.size() - 1; i++) {
			this.edges.add(new Edge2D(vertices.get(i), vertices.get(i + 1)));
		}
		this.edges.add(new Edge2D(vertices.get(i), vertices.get(0)));
	}
	
	/**
	 * Instantiates a new polygon 2d using a 3d polygon with zero in the z values.
	 *
	 * @param polygon the 3d polygon
	 */
	public Polygon2D(Polygon3D polygon) {
		this.edges = new ArrayList<I2DEdge>();
		for (I3DEdge e : polygon.getEdges()) {
			this.edges.add(new Edge2D(e));
		}
	}

	/**
	 * Gets the edges of the polygon.
	 *
	 * @return the edges
	 */
	public List<I2DEdge> getEdges() {
		return this.edges;
	}
	
	/* (non-Javadoc)
	 * @see view.drawing.BaseDrawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		for (I2DEdge e : this.edges)
			e.draw(g);
	}
	
	/* (non-Javadoc)
	 * @see view.drawing.BaseDrawable#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color c) {
		super.setColor(c);
		for (I2DEdge e : this.edges)
			e.setColor(c);
	}
}
