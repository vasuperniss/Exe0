package model.geometry3d;

import java.awt.Graphics;

import view.drawing.BaseDrawable;

// TODO: Auto-generated Javadoc
/**
 * The Class Polygon2D.
 * 
 * @author Michael Vassernis 319582888
 */
public class Edge3D extends BaseDrawable implements I3DEdge {

	/** The start. */
	private I3DVertex start;
	
	/** The end. */
	private I3DVertex end;

	/**
	 * Instantiates a new edge3d.
	 *
	 * @param start the start
	 * @param end the end
	 */
	public Edge3D(I3DVertex start, I3DVertex end) {
		this.start = start;
		this.end = end;
	}
	
	/* (non-Javadoc)
	 * @see model.geometry3d.I3DEdge#getStart()
	 */
	@Override
	public I3DVertex getStart() {
		return this.start;
	}

	/* (non-Javadoc)
	 * @see model.geometry3d.I3DEdge#getEnd()
	 */
	@Override
	public I3DVertex getEnd() {
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

}
