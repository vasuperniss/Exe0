package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Scene implements IModel {
	
	private ArrayList<Point> vertices;
	private ArrayList<Edge> edges;
	private ScanConversionDrawer filler;
	private boolean isFilled;
	
	public Scene() {
		this.vertices = new ArrayList<Point>();
		this.edges = new ArrayList<Edge>();
		this.filler = new ScanConversionDrawer();
		this.isFilled = false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		for (Edge edge : edges) {
			edge.draw(g);
		}
		if (isFilled) {
			this.filler.fillDrawingUsingEdges(g, this.edges);
		}
	}
	
	@Override
	public void setFilled(boolean filled) {
		this.isFilled = filled;
	}
}
