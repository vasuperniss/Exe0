package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * The Class Scene.
 *
 * @author user
 */
public class Scene implements IModel {
	
	/** The vertices of the Scene. */
	private ArrayList<Point> vertices;
	
	/** The main edges of the Scene. */
	private ArrayList<IEdge> edges;
	
	/** The temporary edges of the Scene. */
	private ArrayList<IEdge> tempEdges;
	
	/** The Polygon filler to use when filling the scene. */
	private IPolygonFiller filler;
	
	/** The is filled flag. */
	private boolean isFilled;
	
	/**
	 * Instantiates a new scene.
	 */
	public Scene() {
		this.vertices = new ArrayList<Point>();
		this.edges = new ArrayList<IEdge>();
		this.tempEdges = new ArrayList<IEdge>();
		this.filler = new ScanConversionFiller();
		this.isFilled = false;
	}

	/* (non-Javadoc)
	 * @see model.IModel#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		for (IEdge edge : this.edges) {
			edge.draw(g);
		}
		if (isFilled) {
			this.filler.fillDrawingUsingEdges(g, this.edges);
		}
		g.setColor(Color.RED);
		for (IEdge edge : this.tempEdges) {
			edge.draw(g);
		}
	}
	
	/* (non-Javadoc)
	 * @see model.IModel#setFilled(boolean)
	 */
	@Override
	public void setFilled(boolean filled) {
		this.isFilled = filled;
	}

	/* (non-Javadoc)
	 * @see model.IModel#addTempEdge(int, int, int, int)
	 */
	@Override
	public void addTempEdge(int x1, int y1, int x2, int y2) {
		this.tempEdges.add(new IndexedEdge(new Point(x1, y1),
				new Point(x2, y2), 0, 0));
	}

	/* (non-Javadoc)
	 * @see model.IModel#addTempEdgesToScene()
	 */
	@Override
	public void addTempEdgesToScene() {
		for (IEdge e : this.tempEdges) {
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
