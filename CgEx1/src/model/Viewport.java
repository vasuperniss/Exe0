package model;

import java.io.BufferedReader;
import java.io.IOException;

import model.camera.Camera;
import model.camera.OrthographicCamera;
import model.camera.PerspectiveCamera;
import model.geometry3d.I3DVertex;
import model.geometry3d.Vector3D;
import model.geometry3d.Vertex3D;
import model.matrixLib.Matrix;
import model.matrixLib.Matrix3DFactory;

public class Viewport {

	private Camera camera;
	private int vWidth, vHeight;
	private float left, right, top, bottom;
	private Matrix3DFactory m3dFactory;
	
	private Viewport() {
		this.m3dFactory = new Matrix3DFactory();
	}
	
	public static Viewport fromFile(BufferedReader viewportFileReader) throws IOException {
		Viewport returned = new Viewport();
		String line;
		I3DVertex position = null, lookAt = null;
		Vector3D up = null;
		while ((line = viewportFileReader.readLine()) != null) {
			String[] args = line.split(" ");
			if (args[0].compareToIgnoreCase("_Position_") == 0) {
				position = new Vertex3D(Float.parseFloat(args[1]),
														Float.parseFloat(args[2]),
														Float.parseFloat(args[3]));
			} else if (args[0].compareToIgnoreCase("_LookAt_") == 0) {
				lookAt = new Vertex3D(Float.parseFloat(args[1]),
														Float.parseFloat(args[2]),
														Float.parseFloat(args[3]));
			} else if (args[0].compareToIgnoreCase("_Up_") == 0) {
				up = new Vector3D(Float.parseFloat(args[1]),
													Float.parseFloat(args[2]),
													Float.parseFloat(args[3]));
			} else if (args[0].compareToIgnoreCase("_Window_") == 0) {
				returned.left = Float.parseFloat(args[1]);
				returned.right = Float.parseFloat(args[2]);
				returned.bottom = Float.parseFloat(args[3]);
				returned.top = Float.parseFloat(args[4]);
			} else if (args[0].compareToIgnoreCase("_Viewport_") == 0) {
				returned.vWidth = Integer.parseInt(args[1]);
				returned.vHeight = Integer.parseInt(args[2]);
			}
		}
		returned.camera = new PerspectiveCamera(position, lookAt, up);
		return returned;
	}
	
	public int getWidth() {
		return this.vWidth;
	}
	
	public int getHeight() {
		return this.vHeight;
	}

	public Matrix getCameraWorldMatrix() {
		return this.camera.getTransformationMatrix();
	}

	public Matrix applyBasedOnLookAtOrigin(Matrix m) {
		return this.camera.getLookAtMatrixR().multiply(m).multiply(this.camera.getLookAtMatrix());
	}
	
	public Matrix getViewportMatrix() {
		return this.m3dFactory.getTransformationMatrix(20, 20, 0).multiply(
				this.m3dFactory.getScaleMatrix(this.vWidth / (this.right - this.left),
				this.vHeight / (this.top - this.bottom), 1))
				.multiply(
				this.m3dFactory.getTransformationMatrix(-this.left, -this.bottom, 0))
				.multiply(this.m3dFactory.getScaleMatrix(1, -1, 1));
	}
	
	public Matrix getProjectionMatrix() {
		return this.camera.getProjectionMatrix();
	}

	public float getWindowWidth() {
		return this.right - this.left;
	}
	
	public float getWindowHeight() {
		return this.top - this.bottom;
	}

	public void setSize(int width, int height) {
		this.vWidth = width - 40;
		this.vHeight = height - 40;
	}
}
