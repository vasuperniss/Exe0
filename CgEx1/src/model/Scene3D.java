package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.geometry3d.I3DVertex;
import model.geometry3d.Polygon3D;
import model.geometry3d.Vertex3D;
import controller.IModelController;

public class Scene3D implements IModel{

	private IModelController controller;
	private List<I3DVertex> vertices;
	private List<Polygon3D> polygons;
	private Viewport viewport;

	public Scene3D() {
		this.vertices = new ArrayList<I3DVertex>();
		this.polygons = new ArrayList<Polygon3D>();
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
		this.polygons.clear();
		
		int numVertices = Integer.parseInt(reader.readLine());
		// read all vertices
		for (int i = 0; i < numVertices; i++) {
			String[] vertexStr = reader.readLine().split(" ");
			this.vertices.add(new Vertex3D(Float.parseFloat(vertexStr[0]),
											Float.parseFloat(vertexStr[1]),
											Float.parseFloat(vertexStr[2])));
		}
		
		// read the number of polygons
		int numPolys = Integer.parseInt(reader.readLine());
		// read all Edges
		for (int i = 0; i < numPolys; i++) {
			String[] polyStr = reader.readLine().split(" ");
			List<I3DVertex> vertices = new ArrayList<I3DVertex>();
			for (int j = 0; j < polyStr.length; j++) {
				vertices.add(this.vertices.get(Integer.parseInt(polyStr[j])));
			}
			this.polygons.add(new Polygon3D(vertices));
		}
	}

	@Override
	public void setViewportFromFile(BufferedReader reader) throws IOException {
		this.viewport = Viewport.fromFile(reader);
	}

	@Override
	public Viewport getViewport() {
		return this.viewport;
	}
}
