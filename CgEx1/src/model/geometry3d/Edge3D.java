package model.geometry3d;

import java.awt.Graphics;

import view.drawing.BaseDrawable;

public class Edge3D extends BaseDrawable implements I3DEdge {

	private I3DVertex start;
	private I3DVertex end;

	public Edge3D(I3DVertex start, I3DVertex end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public I3DVertex getStart() {
		return this.start;
	}

	@Override
	public I3DVertex getEnd() {
		return this.end;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawLine((int)this.start.getX(), (int)this.start.getY(),
				(int)this.end.getX(), (int)this.end.getY());
	}

}
