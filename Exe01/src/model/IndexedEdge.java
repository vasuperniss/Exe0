package model;

import java.awt.Point;

public class IndexedEdge implements Edge {

	private int startId, endId;
	private Point start, end;
	
	public IndexedEdge(Point start, Point end, int sId, int eId) {
		this.start = start;
		this.end = end;
		this.startId = sId;
		this.endId = eId;
	}
	
	public Point getStart() {
		return this.start;
	}
	
	public Point getEnd() {
		return this.end;
	}
	
	public String getSaveString() {
		return startId + " " + endId;
	}
}
