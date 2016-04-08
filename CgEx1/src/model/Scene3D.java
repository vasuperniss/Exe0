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
		// read the number of vertices
		int numVertices = Integer.parseInt(sceneFileReader.readLine());
		// read all vertices
		for (int i = 0; i < numVertices; i++) {
			String[] vertexStr = sceneFileReader.readLine().split(" ");
			returned.vertices.add(new Vertex3D(Float.parseFloat(vertexStr[0]),
											Float.parseFloat(vertexStr[1]),
											Float.parseFloat(vertexStr[2])));
		}
		
		// read the number of edges
		int numEdges = Integer.parseInt(sceneFileReader.readLine());
		// read all Edges
		for (int i = 0; i < numEdges; i++) {
			String[] edgeStr = sceneFileReader.readLine().split(" ");
			int startId = Integer.parseInt(edgeStr[0]);
			int endId = Integer.parseInt(edgeStr[1]);
			returned.edges.add(new Edge3D(returned.vertices.get(startId),
												returned.vertices.get(endId)));
		}
		
		// read viewport
		returned.viewport = Viewport.fromFile(viewportFileReader);
		
		return returned;
	}
}
