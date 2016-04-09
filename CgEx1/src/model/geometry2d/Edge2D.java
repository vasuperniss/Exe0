package model.geometry2d;

import java.awt.Graphics;

public class Edge2D implements I2DEdge {

	private I2DVertex start;
	private I2DVertex end;
	private float slope;

	public Edge2D(I2DVertex start, I2DVertex end) {
		this.start = start;
		this.end = end;
		this.slope =  (this.end.getY() - this.start.getY())
				/ (this.end.getX() - this.start.getX());
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
