package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Scene implements IModel {
	
	private ArrayList<Point> vertices;
	private ArrayList<Edge> edges;
	private ArrayList<Edge> tempEdges;
	private ScanConversionDrawer filler;
	private boolean isFilled;
	
	public Scene() {
		this.vertices = new ArrayList<Point>();
		this.edges = new ArrayList<Edge>();
		this.tempEdges = new ArrayList<Edge>();
		this.filler = new ScanConversionDrawer();
		this.isFilled = false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		for (Edge edge : this.edges) {
			edge.draw(g);
		}
		if (isFilled) {
			this.filler.fillDrawingUsingEdges(g, this.edges);
		}
		g.setColor(Color.RED);
		for (Edge edge : this.tempEdges) {
			edge.draw(g);
		}
	}
	
	@Override
	public void setFilled(boolean filled) {
		this.isFilled = filled;
	}

	@Override
	public void addTempEdge(int x1, int y1, int x2, int y2) {
		this.tempEdges.add(new IndexedEdge(new Point(x1, y1),
				new Point(x2, y2), 0, 0));
	}

	@Override
	public void addTempEdgesToScene() {
		for (Edge e : this.tempEdges) {
			this.vertices.add(new Point(e.getStart().x, e.getStart().y));
			this.edges.add(new IndexedEdge(e.getStart(), e.getEnd(),
					this.vertices.size() - 1, this.vertices.size()));
		}
		this.edges.add(new IndexedEdge(
				this.tempEdges.get(this.tempEdges.size() - 1).getEnd(),
				this.tempEdges.get(0).getStart(), this.vertices.size() - 1, 
				this.vertices.size() - 1 - this.tempEdges.size()));
		this.tempEdges.clear();
	}
}
