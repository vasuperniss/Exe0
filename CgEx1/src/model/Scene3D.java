package model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import view.drawing.IDrawable;
import model.clipping.CohenSutherlandClipper;
import model.clipping.ILineClipper;
import model.filling.IPolygonFiller;
import model.filling.ScanConversionFiller;
import model.geometry2d.Polygon2D;
import model.geometry3d.I3DVertex;
import model.geometry3d.Polygon3D;
import model.geometry3d.Vertex3D;
import model.matrixLib.Matrix;
import model.matrixLib.Matrix3DFactory;

/**
 * The Scene3d class.
 *
 * @author Michael Vassernis 319582888
 */
public class Scene3D implements IModel{

	/** The vertices in the scene. */
	private List<I3DVertex> vertices;
	
	/** The polygons in the scene. */
	private List<Polygon3D> polygons;
	
	/** The viewport. */
	private Viewport viewport;
	
	/** The matrix3d factory. */
	private Matrix3DFactory m3dFactory;
	
	/** The modifying matrix. */
	private Matrix modifingMatrix;
	
	/** The tmp modifying matrix. */
	private Matrix tmpModifingMatrix;
	
	/** The is filled boolean. */
	private boolean isFilled;
	
	/** The filler object. */
	private IPolygonFiller filler;
	
	/** The Random object (for random colors). */
	private Random r;
	
	/** The clipper object. */
	private ILineClipper clipper;
	
	/** The is clipping on boolean. */
	private boolean isClippingOn;
	
	/** The clipping polygon border display. */
	private Polygon3D clippingPoly;

	/**
	 * Instantiates a new scene 3d.
	 */
	public Scene3D() {
		this.vertices = new ArrayList<I3DVertex>();
		this.polygons = new ArrayList<Polygon3D>();
		this.r = new Random();
		// matrix, filling & clipping makers
		this.m3dFactory = new Matrix3DFactory();
		this.filler = new ScanConversionFiller();
		this.clipper = new CohenSutherlandClipper(0, 1, 0, 1);
		// defualt settings initializing
		this.modifingMatrix = this.m3dFactory.getIdentityMatrix();
		this.tmpModifingMatrix = this.m3dFactory.getIdentityMatrix();
		this.isFilled = false;
		this.isClippingOn = false;
	}
	
	/**
	 * creates a new Scene3d From file.
	 *
	 * @param sceneFileReader the scene file reader
	 * @param viewportFileReader the viewport file reader
	 * @return the scene3d
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Scene3D fromFile(BufferedReader sceneFileReader,
									BufferedReader viewportFileReader)
									throws NumberFormatException, IOException {
		Scene3D returned = new Scene3D();
		returned.setSceneFromFile(sceneFileReader);
		returned.setViewportFromFile(viewportFileReader);
		return returned;
	}

	/* (non-Javadoc)
	 * @see model.IModel#setSceneFromFile(java.io.BufferedReader)
	 */
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
			// give the polygon a random color
			p.setColor(this.randomColor());
			this.polygons.add(p);
		}
	}

	/**
	 * Random color.
	 *
	 * @return the color
	 */
	private Color randomColor() {
		return new Color(this.r.nextInt(150)
								,this.r.nextInt(150)
								,this.r.nextInt(150));
	}

	/* (non-Javadoc)
	 * @see model.IModel#setViewportFromFile(java.io.BufferedReader)
	 */
	@Override
	public void setViewportFromFile(BufferedReader reader) throws IOException {
		this.viewport = Viewport.fromFile(reader);
		// new clipper based on the viewport details
		this.clipper = new CohenSutherlandClipper(20, 20,
												this.viewport.getWidth() + 20,
												this.viewport.getHeight() + 20);
		// create the clipping polygon (for display perpeses only)
		List<I3DVertex> clippingCorners = new ArrayList<I3DVertex>();
		clippingCorners.add(new Vertex3D(20, 20, 0));
		clippingCorners.add(new Vertex3D(this.viewport.getWidth() + 20, 20, 0));
		clippingCorners.add(new Vertex3D(this.viewport.getWidth() + 20,
											this.viewport.getHeight() + 20, 0));
		clippingCorners.add(new Vertex3D(20, this.viewport.getHeight() + 20, 0));
		this.clippingPoly = new Polygon3D(clippingCorners);
		this.clippingPoly.setColor(Color.BLACK);
	}

	/* (non-Javadoc)
	 * @see model.IModel#getViewport()
	 */
	@Override
	public Viewport getViewport() {
		return this.viewport;
	}

	/* (non-Javadoc)
	 * @see model.IModel#reset()
	 */
	@Override
	public void reset() {
		this.modifingMatrix = this.m3dFactory.getIdentityMatrix();
		this.tmpModifingMatrix = this.m3dFactory.getIdentityMatrix();
	}

	/* (non-Javadoc)
	 * @see model.IModel#to2DDrawing()
	 */
	@Override
	public List<IDrawable> to2DDrawing() {
		List<IDrawable> drawables = new ArrayList<IDrawable>();
		if (this.isClippingOn && this.clippingPoly != null) {
			// draw the clipping polygon
			drawables.add(clippingPoly);
		}
		// create the joined matrix of all modifications and view matrixes
		Matrix full = this.viewport.getViewportMatrix()
				.multiply(this.viewport.getProjectionMatrix())
				.multiply(this.tmpModifingMatrix)
				.multiply(this.modifingMatrix)
				.multiply(this.viewport.getCameraWorldMatrix());
		for (Polygon3D p : this.polygons) {
			// draw all the the polygons
			Polygon2D newP = new Polygon2D(p.applyMatrix(full));
			newP.setColor(p.getColor());
			if (this.isClippingOn) {
				newP = this.clipper.clipPolygon(newP);
				if (newP == null)
					// polygon was entirely outside the window
					continue;
			}
			drawables.add(newP);
			if (this.isFilled) {
				// fills the polygon
				drawables.addAll(this.filler.fillDrawingUsingEdges(
									newP.getEdges(), newP.getColor()));
			}
		}
		if (this.isClippingOn && this.clippingPoly != null) {
			// draw the clipping polygon (again - first time was for no flickering)
			drawables.add(clippingPoly);
		}
		return drawables;
	}

	/* (non-Javadoc)
	 * @see model.IModel#setTmpTransform(int, int)
	 */
	@Override
	public void setTmpTransform(int diffX, int diffY) {
		float xDiff = diffX * (this.viewport.getWindowWidth()
												/ this.viewport.getWidth());
		float yDiff = diffY * (this.viewport.getWindowHeight()
												/ this.viewport.getHeight());
		this.tmpModifingMatrix = m3dFactory.getTransformationMatrix(xDiff, yDiff, 0);
	}

	/* (non-Javadoc)
	 * @see model.IModel#setTmpScale(float)
	 */
	@Override
	public void setTmpScale(float scale) {
		this.tmpModifingMatrix = this.viewport.applyBasedOnLookAtOrigin(
				m3dFactory.getScaleMatrix(scale));
	}

	/* (non-Javadoc)
	 * @see model.IModel#setTmpRotation(float, model.matrixLib.Matrix3DFactory.Axis)
	 */
	@Override
	public void setTmpRotation(float deg, Matrix3DFactory.Axis axis) {
		this.tmpModifingMatrix = this.viewport.applyBasedOnLookAtOrigin(
				m3dFactory.getRotationMatrixDeg(deg, axis));
	}

	/* (non-Javadoc)
	 * @see model.IModel#addModifications()
	 */
	@Override
	public void addModifications() {
		this.modifingMatrix = this.tmpModifingMatrix.multiply(this.modifingMatrix);
		this.tmpModifingMatrix = m3dFactory.getIdentityMatrix();
	}

	/* (non-Javadoc)
	 * @see model.IModel#setFilled(boolean)
	 */
	@Override
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	/* (non-Javadoc)
	 * @see model.IModel#setViewportSize(int, int)
	 */
	@Override
	public void setViewportSize(int width, int height) {
		this.viewport.setSize(width, height);
		this.clipper = new CohenSutherlandClipper(20, 20,
							this.viewport.getWidth() + 20,
							this.viewport.getHeight() + 20);
		// create the clipping polygon (for display perpeses only)
		List<I3DVertex> clippingCorners = new ArrayList<I3DVertex>();
		clippingCorners.add(new Vertex3D(20, 20, 0));
		clippingCorners.add(new Vertex3D(this.viewport.getWidth() + 20, 20, 0));
		clippingCorners.add(new Vertex3D(this.viewport.getWidth() + 20,
											this.viewport.getHeight() + 20, 0));
		clippingCorners.add(new Vertex3D(20, this.viewport.getHeight() + 20, 0));
		this.clippingPoly = new Polygon3D(clippingCorners);
		this.clippingPoly.setColor(Color.BLACK);
	}

	/* (non-Javadoc)
	 * @see model.IModel#setClipped(boolean)
	 */
	@Override
	public void setClipped(boolean isClipped) {
		this.isClippingOn = isClipped;
	}
}
