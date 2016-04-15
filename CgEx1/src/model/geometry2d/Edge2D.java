package model.geometry2d;

import java.awt.Graphics;

import view.drawing.BaseDrawable;
import model.geometry3d.I3DEdge;

/**
 * The Class Edge2D.
 * 
 * @author Michael Vassernis 319582888
 */
public class Edge2D extends BaseDrawable implements I2DEdge {

	/** The start. */
	private I2DVertex start;
	
	/** The end. */
	private I2DVertex end;
	
	/** The slope. */
	private float slope;

	/**
	 * Instantiates a new edge 2d using two vertexes.
	 *
	 * @param start the start
	 * @param end the end
	 */
	public Edge2D(I2DVertex start, I2DVertex end) {
		this.start = start;
		this.end = end;
		this.slope =  (this.end.getY() - this.start.getY())
				/ (this.end.getX() - this.start.getX());
	}
	
	/**
	 * Instantiates a new edge 2d using two vertexes.
	 *
	 * @param x1 the x1
	 * @param y1 the y1
	 * @param x2 the x2
	 * @param y2 the y2
	 */
	public Edge2D(float x1, float y1, float x2, float y2) {
		this(new Vertex2D(x1, y1), new Vertex2D(x2, y2));
	}
	
	/**
	 * Instantiates a new edge 2d using a 3d edge with zeros in the Z values.
	 *
	 * @param edge the 3d edge
	 */
	public Edge2D(I3DEdge edge) {
		this(edge.getStart(), edge.getEnd());
		this.setColor(edge.getColor());
	}
	
	/* (non-Javadoc)
	 * @see model.geometry2d.I2DEdge#getStart()
	 */
	@Override
	public I2DVertex getStart() {
		return this.start;
	}

	/* (non-Javadoc)
	 * @see model.geometry2d.I2DEdge#getEnd()
	 */
	@Override
	public I2DVertex getEnd() {
		return this.end;
	}

	/* (non-Javadoc)
	 * @see view.drawing.BaseDrawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawLine((int)this.start.getX(), (int)this.start.getY(),
				(int)this.end.getX(), (int)this.end.getY());
	}

	/* (non-Javadoc)
	 * @see model.geometry2d.I2DEdge#getSlope()
	 */
	@Override
	public float getSlope() {
		return this.slope;
	}

	/* (non-Javadoc)
	 * @see model.geometry2d.I2DEdge#getIntVertexAtY(float)
	 */
	@Override
	public I2DVertex getIntVertexAtY(float y) {
		float t = (y - this.start.getY()) / (this.end.getY() - this.start.getY());
		float x = this.start.getX() + t * (this.end.getX() - this.start.getX());
		return new Vertex2D((int)x, (int)y);
	}

	/* (non-Javadoc)
	 * @see model.geometry2d.I2DEdge#getIntVertexAtX(float)
	 */
	@Override
	public I2DVertex getIntVertexAtX(float x) {
		float t = (x - this.start.getX()) / (this.end.getX() - this.start.getX());
		float y = this.start.getY() + t * (this.end.getY() - this.start.getY());
		return new Vertex2D((int)x, (int)y);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("(%f,%f)->(%f,%f)", this.start.getX(), this.start.getY()
						, this.end.getX(), this.end.getY());
	}

}
