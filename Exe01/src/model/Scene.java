package model;

import java.awt.Point;
import java.util.ArrayList;

public class Scene {
	
	private enum State {Drawing, Stale, Fill};
	
	private ArrayList<Point> vertices;
	private ArrayList<IndexedEdge> edges;
	private State state;
	
	public Scene() {
		this.vertices = new ArrayList<Point>();
		this.edges = new ArrayList<IndexedEdge>();
		this.state = State.Stale;
	}
}
