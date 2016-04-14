package model.geometry2d;

import java.awt.Graphics;

import view.drawing.BaseDrawable;
import model.geometry3d.I3DEdge;

public class Edge2D extends BaseDrawable implements I2DEdge {

	private I2DVertex start;
	private I2DVertex end;
	private float slope;

	public Edge2D(I2DVertex start, I2DVertex end) {
		this.start = start;
		this.end = end;
		this.slope =  (this.end.getY() - this.start.getY())
				/ (this.end.getX() - this.start.getX());
	}
	
	public Edge2D(float x1, float y1, float x2, float y2) {
		this(new Vertex2D(x1, y1), new Vertex2D(x2, y2));
	}
	
	public Edge2D(I3DEdge edge) {
		this(edge.getStart(), edge.getEnd());
		this.setColor(edge.getColor());
	}
	
	@Override
	public I2DVertex getStart() {
		return this.start;
	}

	@Override
	public I2DVertex getEnd() {
		return this.end;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawLine((int)this.start.getX(), (int)this.start.getY(),
				(int)this.end.getX(), (int)this.end.getY());
	}

	@Override
	public float getSlope() {
		return this.slope;
	}

	@Override
	public I2DVertex getVertexAtY(float y) {
		float t = (y - this.start.getY()) / (this.end.getY() - this.start.getY());
		float x = this.start.getX() + t * (this.end.getX() - this.start.getX());
		return new Vertex2D(x, y);
	}

	@Override
	public I2DVertex getVertexAtX(float x) {
		float t = (x - this.start.getX()) / (this.end.getX() - this.start.getX());
		float y = this.start.getY() + t * (this.end.getY() - this.start.getY());
		return new Vertex2D(x, y);
	}

}
