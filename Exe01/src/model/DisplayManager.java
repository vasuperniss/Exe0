package model;

import java.util.ArrayList;

public class DisplayManager {
	
	private enum State {Drawing, Stale, Fill};
	
	private ArrayList<Polygon> polygons;
	private State state;
	
	public DisplayManager() {
		this.polygons = new ArrayList<Polygon>();
		this.state = State.Stale;
	}
}
