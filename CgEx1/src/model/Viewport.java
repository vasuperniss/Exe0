package model;

import java.io.BufferedReader;
import java.io.IOException;

import model.camera.Camera;
import model.camera.OrthographicCamera;
import model.geometry3d.I3DVertex;
import model.geometry3d.Vector3D;
import model.geometry3d.Vertex3D;
import model.matrixLib.Matrix;
import model.matrixLib.Matrix3DFactory;

/**
 * The Viewport class.
 *
 * @author Michael Vassernis 319582888
 */
public class Viewport {

	/** The camera. */
	private Camera camera;
	
	/** The viewport height & width. */
	private int vWidth, vHeight;
	
	/** The bottom left right and top bounds. */
	private float left, right, top, bottom;
	
	/** The mmatrix3d factory. */
	private Matrix3DFactory m3dFactory;
	
	/**
	 * Instantiates a new viewport.
	 */
	private Viewport() {
		this.m3dFactory = new Matrix3DFactory();
	}
	
	/**
	 * creates a new Viewport From file.
	 *
	 * @param viewportFileReader the viewport file reader
	 * @return the viewport
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Viewport fromFile(BufferedReader viewportFileReader) throws IOException {
		Viewport returned = new Viewport();
		String line;
		I3DVertex position = null, lookAt = null;
		Vector3D up = null;
		while ((line = viewportFileReader.readLine()) != null) {
			String[] args = line.split(" ");
			if (args[0].compareToIgnoreCase("_Position_") == 0) {
				// read the camera position line
				position = new Vertex3D(Float.parseFloat(args[1]),
										Float.parseFloat(args[2]),
										Float.parseFloat(args[3]));
			} else if (args[0].compareToIgnoreCase("_LookAt_") == 0) {
				// read the camera lookAt line
				lookAt = new Vertex3D(Float.parseFloat(args[1]),
										Float.parseFloat(args[2]),
										Float.parseFloat(args[3]));
			} else if (args[0].compareToIgnoreCase("_Up_") == 0) {
				// read the camera up vector line
				up = new Vector3D(Float.parseFloat(args[1]),
										Float.parseFloat(args[2]),
										Float.parseFloat(args[3]));
			} else if (args[0].compareToIgnoreCase("_Window_") == 0) {
				// read the camera window line
				returned.left = Float.parseFloat(args[1]);
				returned.right = Float.parseFloat(args[2]);
				returned.bottom = Float.parseFloat(args[3]);
				returned.top = Float.parseFloat(args[4]);
			} else if (args[0].compareToIgnoreCase("_Viewport_") == 0) {
				// read the screen viewport line
				returned.vWidth = Integer.parseInt(args[1]);
				returned.vHeight = Integer.parseInt(args[2]);
			}
		}
		// create an orthographic camera with the loaded variables
		returned.camera = new OrthographicCamera(position, lookAt, up);
		return returned;
	}
	
	/**
	 * Gets the width of the viewport.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return this.vWidth;
	}
	
	/**
	 * Gets the height of the viewport.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return this.vHeight;
	}

	/**
	 * Gets the camera world matrix.
	 *
	 * @return the camera world matrix
	 */
	public Matrix getCameraWorldMatrix() {
		return this.camera.getTransformationMatrix();
	}

	/**
	 * Apply based on look at vector as origin.
	 *
	 * @param m the matrix to apply
	 * @return the joined matrix
	 */
	public Matrix applyBasedOnLookAtOrigin(Matrix m) {
		return this.camera.getLookAtMatrixR()
									.multiply(m)
									.multiply(this.camera.getLookAtMatrix());
	}
	
	/**
	 * Gets the viewport matrix.
	 *
	 * @return the viewport matrix
	 */
	public Matrix getViewportMatrix() {
		// flip y axis -> move to 0,0 -> scale to window -> move 20,20 up
		return this.m3dFactory.getTransformationMatrix(20, 20, 0).multiply(
				this.m3dFactory.getScaleMatrix(this.vWidth / (this.right - this.left),
										this.vHeight / (this.top - this.bottom), 1))
																.multiply(
				this.m3dFactory.getTransformationMatrix(-this.left, -this.bottom, 0))
																.multiply(
											this.m3dFactory.getScaleMatrix(1, -1, 1));
	}
	
	/**
	 * Gets the projection matrix.
	 *
	 * @return the projection matrix
	 */
	public Matrix getProjectionMatrix() {
		return this.camera.getProjectionMatrix();
	}

	/**
	 * Gets the camera window width.
	 *
	 * @return the window width
	 */
	public float getWindowWidth() {
		return this.right - this.left;
	}
	
	/**
	 * Gets the camera window height.
	 *
	 * @return the window height
	 */
	public float getWindowHeight() {
		return this.top - this.bottom;
	}

	/**
	 * Sets the size of the viewport.
	 *
	 * @param width the new width
	 * @param height the new height
	 */
	public void setSize(int width, int height) {
		this.vWidth = width - 40;
		this.vHeight = height - 40;
	}

	public I3DVertex getCameraPosition() {
		return this.camera.getPosition();
	}
}
