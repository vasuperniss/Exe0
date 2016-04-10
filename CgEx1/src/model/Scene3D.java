package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.geometry3d.I3DEdge;
import model.geometry3d.I3DVertex;
import model.geometry3d.Edge3D;
import model.geometry3d.Vertex3D;
import controller.IModelController;

public class Scene3D implements IModel{

	private IModelController controller;
	private List<I3DVertex> vertices;
	private List<I3DEdge> edges;
	private Viewport viewport;

	public Scene3D() {
		this.vertices = new ArrayList<I3DVertex>();
		this.edges = new ArrayList<I3DEdge>();
	}
	
	@Override
	public void setController(IModelController controller) {
		this.controller = controller;
	}
	
	public static Scene3D fromFile(BufferedReader sceneFileReader,
									BufferedReader viewportFileReader)
									throws NumberFormatException, IOException {
		Scene3D returned = new Scene3D();
		returned.setSceneFromFile(sceneFileReader);
		returned.setViewportFromFile(viewportFileReader);
		return returned;
	}

	@Override
	public void setSceneFromFile(BufferedReader reader) throws IOException {
		// delete the current state
		this.vertices.clear();
		this.edges.clear();
		
		int numVertices = Integer.parseInt(reader.readLine());
		// read all vertices
		for (int i = 0; i < numVertices; i++) {
			String[] vertexStr = reader.readLine().split(" ");
			this.vertices.add(new Vertex3D(Float.parseFloat(vertexStr[0]),
											Float.parseFloat(vertexStr[1]),
											Float.parseFloat(vertexStr[2])));
		}
		
		// read the number of edges
		int numEdges = Integer.parseInt(reader.readLine());
		// read all Edges
		for (int i = 0; i < numEdges; i++) {
			String[] edgeStr = reader.readLine().split(" ");
			int startId = Integer.parseInt(edgeStr[0]);
			int endId = Integer.parseInt(edgeStr[1]);
			this.edges.add(new Edge3D(this.vertices.get(startId),
												this.vertices.get(endId)));
		}
	}

	@Override
	public void setViewportFromFile(BufferedReader reader) throws IOException {
		this.viewport = Viewport.fromFile(reader);
	}
}
