package model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import view.drawing.IDrawable;
import view.drawing.IPolygonFiller;
import view.drawing.ScanConversionFiller;
import model.clipping.CohenSutherlandClipper;
import model.clipping.ILineClipper;
import model.geometry2d.Polygon2D;
import model.geometry3d.I3DVertex;
import model.geometry3d.Polygon3D;
import model.geometry3d.Vertex3D;
import model.matrixLib.Matrix;
import model.matrixLib.Matrix3DFactory;

public class Scene3D implements IModel{

	private List<I3DVertex> vertices;
	private List<Polygon3D> polygons;
	private Viewport viewport;
	
	private Matrix3DFactory m3dFactory;
	private Matrix modifingMatrix;
	private Matrix tmpModifingMatrix;
	private boolean isFilled;
	private IPolygonFiller filler;
	private Random r;
	private ILineClipper clipper;
	private boolean isClippingOn;
	private Polygon3D clippingPoly;

	public Scene3D() {
		this.vertices = new ArrayList<I3DVertex>();
		this.polygons = new ArrayList<Polygon3D>();
		
		this.m3dFactory = new Matrix3DFactory();
		this.modifingMatrix = this.m3dFactory.getIdentityMatrix();
		this.tmpModifingMatrix = this.m3dFactory.getIdentityMatrix();
		this.filler = new ScanConversionFiller();
		this.clipper = new CohenSutherlandClipper(0, 1, 0, 1);
		this.isFilled = false;
		this.isClippingOn = false;
		this.r = new Random();
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
			Polygon3D p = new Polygon3D(vertices);
			p.setColor(this.randomColor());
			this.polygons.add(p);
		}
	}

	private Color randomColor() {
		return new Color(this.r.nextInt(256)
						,this.r.nextInt(256)
						,this.r.nextInt(256));
	}

	@Override
	public void setViewportFromFile(BufferedReader reader) throws IOException {
		this.viewport = Viewport.fromFile(reader);
		this.clipper = new CohenSutherlandClipper(20, 20,
				this.viewport.getWidth() + 20, this.viewport.getHeight() + 20);
		List<I3DVertex> clippingCorners = new ArrayList<I3DVertex>();
		clippingCorners.add(new Vertex3D(20, 20, 0));
		clippingCorners.add(new Vertex3D(this.viewport.getWidth() + 20, 20, 0));
		clippingCorners.add(new Vertex3D(this.viewport.getWidth() + 20, this.viewport.getHeight() + 20, 0));
		clippingCorners.add(new Vertex3D(20, this.viewport.getHeight() + 20, 0));
		this.clippingPoly = new Polygon3D(clippingCorners);
		this.clippingPoly.setColor(Color.BLACK);
	}

	@Override
	public Viewport getViewport() {
		return this.viewport;
	}

	@Override
	public void reset() {
		this.modifingMatrix = this.m3dFactory.getIdentityMatrix();
		this.tmpModifingMatrix = this.m3dFactory.getIdentityMatrix();
	}

	@Override
	public List<IDrawable> to2DDrawing() {
		List<IDrawable> drawables = new ArrayList<IDrawable>();
		if (this.isClippingOn && this.clippingPoly != null) {
			drawables.add(clippingPoly);
		}
		Matrix full = this.viewport.getViewportMatrix()
				.multiply(this.viewport.getProjectionMatrix())
				.multiply(this.tmpModifingMatrix)
				.multiply(this.modifingMatrix)
				.multiply(this.viewport.getCameraWorldMatrix());
		for (Polygon3D p : this.polygons) {
			Polygon2D newP = new Polygon2D(p.applyMatrix(full));
			newP.setColor(p.getColor());
			if (this.isClippingOn) {
				newP = this.clipper.clipPolygon(newP);
				if (newP == null)
					continue;
			}
			drawables.add(newP);
			if (this.isFilled) {
				drawables.addAll(this.filler.fillDrawingUsingEdges(newP.getEdges(), newP.getColor()));
			}
		}
		if (this.isClippingOn && this.clippingPoly != null) {
			drawables.add(clippingPoly);
		}
		return drawables;
	}

	@Override
	public void setTmpTransform(int diffX, int diffY) {
		float xDiff = diffX * (this.viewport.getWindowWidth() / this.viewport.getWidth());
		float yDiff = diffY * (this.viewport.getWindowHeight() / this.viewport.getHeight());
		this.tmpModifingMatrix = m3dFactory.getTransformationMatrix(xDiff, yDiff, 0);
	}

	@Override
	public void setTmpScale(float scale) {
		this.tmpModifingMatrix = this.viewport.applyBasedOnLookAtOrigin(
				m3dFactory.getScaleMatrix(scale));
	}

	@Override
	public void setTmpRotation(float deg, Matrix3DFactory.Axis axis) {
		this.tmpModifingMatrix = this.viewport.applyBasedOnLookAtOrigin(
				m3dFactory.getRotationMatrixDeg(deg, axis));
	}

	@Override
	public void addModifications() {
		this.modifingMatrix = this.tmpModifingMatrix.multiply(this.modifingMatrix);
		this.tmpModifingMatrix = m3dFactory.getIdentityMatrix();
	}

	@Override
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	@Override
	public void setViewportSize(int width, int height) {
		this.viewport.setSize(width, height);
	}

	@Override
	public void setClipped(boolean isClipped) {
		this.isClippingOn = isClipped;
	}
}
