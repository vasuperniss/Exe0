package model.geometry3d;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import model.matrixLib.Matrix;
import view.drawing.BaseDrawable;

/**
 * The Class Polygon2D.
 * 
 * @author Michael Vassernis 319582888
 */
public class Polygon3D extends BaseDrawable {

	/** The edges. */
	private List<I3DEdge> edges;
	
	/** The vertices. */
	private List<I3DVertex> vertices;
	
	/**
	 * Instantiates a new polygon3d using a list of vertices.
	 *
	 * @param vertices the vertices
	 */
	public Polygon3D(List<I3DVertex> vertices) {
		if (vertices.size() < 3)
			throw new RuntimeException("Not enought vertices.");
		
		this.vertices = vertices;
		this.edges = new ArrayList<I3DEdge>();
		int i;
		// creates the polygon edges based on the order of the vertices
		for (i = 0; i < vertices.size() - 1; i++) {
			this.edges.add(new Edge3D(vertices.get(i), vertices.get(i + 1)));
		}
		this.edges.add(new Edge3D(vertices.get(i), vertices.get(0)));
	}
	
	/**
	 * Gets the edges of the polygon.
	 *
	 * @return the edges
	 */
	public List<I3DEdge> getEdges() {
		return this.edges;
	}

	/**
	 * Apply matrix on the polygon.
	 *
	 * @param matrix the matrix
	 * @return the new polygon3d
	 */
	public Polygon3D applyMatrix(Matrix matrix) {
		List<I3DVertex> modified = new ArrayList<I3DVertex>();
		for (I3DVertex v : this.vertices)
			modified.add(v.applyMatrix(matrix));
		Polygon3D p = new Polygon3D(modified);
		p.setColor(this.color);
		return p;
	}
	
	/* (non-Javadoc)
	 * @see view.drawing.BaseDrawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		for (I3DEdge e : this.edges)
			e.draw(g);
	}
	
	/* (non-Javadoc)
	 * @see view.drawing.BaseDrawable#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color c) {
		super.setColor(c);
		for (I3DEdge e : this.edges)
			e.setColor(c);
	}

	public I3DVertex getCenter() {
		float xC = 0, yC = 0, zC = 0;
		for (I3DVertex v : this.vertices) {
			xC += v.getX();
			yC += v.getY();
			zC += v.getZ();
		}
		return new Vertex3D(xC / this.vertices.size(),
							yC / this.vertices.size(),
							zC / this.vertices.size());
	}
}
