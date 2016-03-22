package model;

import java.awt.Graphics;
import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class IndexedEdge.
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public class IndexedEdge implements IEdge {

	/** The start and end array id of the vertices. */
	private int startId, endId;
	
	/** The start and end Vertices. */
	private Point start, end;
	
	/**
	 * Instantiates a new indexed edge.
	 *
	 * @param start the start Vertex
	 * @param end the end Vertex
	 * @param sId the array id of the start vertex
	 * @param eId the array id of the end vertex
	 */
	public IndexedEdge(Point start, Point end, int sId, int eId) {
		this.start = start;
		this.end = end;
		this.startId = sId;
		this.endId = eId;
	}
	
	/* (non-Javadoc)
	 * @see model.IEdge#getStart()
	 */
	public Point getStart() {
		return this.start;
	}
	
	/* (non-Javadoc)
	 * @see model.IEdge#getEnd()
	 */
	public Point getEnd() {
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
		g.drawLine(this.start.x, this.start.y,
				this.end.x, this.end.y);
	}
}
