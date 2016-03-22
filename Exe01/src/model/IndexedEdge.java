package model;

import java.awt.Graphics;

/**
 * The Class IndexedEdge.
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public class IndexedEdge implements IEdge {

	/** The start and end array id of the vertices. */
	private int startId, endId;
	
	/** The start and end Vertices. */
	private Vertex start, end;

	/** The slope. */
	private float slope;
	
	/**
	 * Instantiates a new indexed edge.
	 *
	 * @param start the start Vertex
	 * @param end the end Vertex
	 * @param sId the array id of the start vertex
	 * @param eId the array id of the end vertex
	 */
	public IndexedEdge(Vertex start, Vertex end, int sId, int eId) {
		this.start = start;
		this.end = end;
		this.startId = sId;
		this.endId = eId;
		this.slope =  (this.end.y - this.start.y)
				/ (this.end.x - this.start.x);
	}
	
	/* (non-Javadoc)
	 * @see model.IEdge#getStart()
	 */
	public Vertex getStart() {
		return this.start;
	}
	
	/* (non-Javadoc)
	 * @see model.IEdge#getEnd()
	 */
	public Vertex getEnd() {
		return this.end;
	}
	
	/**
	 * Gets the save string.
	 *
	 * @return the save string
	 */
	public String getSaveString() {
		return startId + " " + endId;
	}

	/* (non-Javadoc)
	 * @see model.IEdge#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		g.drawLine((int)this.start.x, (int)this.start.y,
				(int)this.end.x, (int)this.end.y);
	}

	/* (non-Javadoc)
	 * @see model.IEdge#getSlope()
	 */
	@Override
	public float getSlope() {
		return this.slope;
	}
}
