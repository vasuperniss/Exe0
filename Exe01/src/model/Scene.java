package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The Class Scene.
 *
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
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
		} else {
			g.setColor(Color.RED);
			for (IEdge edge : this.tempEdges) {
				edge.draw(g);
			}
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
		this.vertices.add(this.tempEdges.get(this.tempEdges.size() - 1).getEnd());
		this.edges.add(new IndexedEdge(
				this.tempEdges.get(this.tempEdges.size() - 1).getEnd(),
				this.tempEdges.get(0).getStart(), this.vertices.size() - 1, 
				this.vertices.size() - 1 - this.tempEdges.size()));
		this.tempEdges.clear();
	}

	/* (non-Javadoc)
	 * @see model.IModel#writeDataTo(java.io.PrintWriter)
	 */
	@Override
	public void writeDataTo(PrintWriter writer) {
		// write the number of vertices
		writer.println(this.vertices.size());
		// write all Vertices
		for (Point v : this.vertices) {
			writer.println(v.x + " " + v.y);
		}
		
		// write the number of edges
		writer.println(this.edges.size());
		// write all edges based on vertex indexes
		for (IEdge ie : this.edges) {
			writer.println(((IndexedEdge)ie).getSaveString());
		}
	}

	/* (non-Javadoc)
	 * @see model.IModel#loadDataFrom(java.io.BufferedReader)
	 */
	@Override
	public void loadDataFrom(BufferedReader reader) throws NumberFormatException, IOException {
		// read the number of vertices
		int numVertices = Integer.parseInt(reader.readLine());
		// read all vertices
		ArrayList<Point> loadedVertices = new ArrayList<Point>();
		for (int i = 0; i < numVertices; i++) {
			String[] vertexStr = reader.readLine().split(" ");
			loadedVertices.add(new Point(Integer.parseInt(vertexStr[0]),
										Integer.parseInt(vertexStr[1])));
		}
		
		// read the number of edges
		int numEdges = Integer.parseInt(reader.readLine());
		// read all Edges
		ArrayList<IEdge> loadedEdges = new ArrayList<IEdge>();
		for (int i = 0; i < numEdges; i++) {
			String[] edgeStr = reader.readLine().split(" ");
			int startId = Integer.parseInt(edgeStr[0]);
			int endId = Integer.parseInt(edgeStr[1]);
			loadedEdges.add(new IndexedEdge(loadedVertices.get(startId),
								loadedVertices.get(endId), startId, endId));
		}
		
		// reset Scene to loaded Data
		this.edges = loadedEdges;
		this.vertices = loadedVertices;
		this.isFilled = false;
		this.tempEdges = new ArrayList<IEdge>();
	}
}
