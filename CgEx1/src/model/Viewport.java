package model;

import java.io.BufferedReader;
import java.io.IOException;

import model.camera.Camera;
import model.camera.OrthographicCamera;
import model.geometry3d.I3DVertex;
import model.geometry3d.Vector3D;
import model.geometry3d.Vertex3D;

public class Viewport {

	private Camera camera;
	private int vWidth, vHeight;
	private float left, right, top, bottom;
	
	private Viewport() {
		
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
		returned.camera = new OrthographicCamera(position, lookAt, up);
		return returned;
	}
	
	public int getWidth() {
		return this.vWidth;
	}
	
	public int getHeight() {
		return this.vHeight;
	}
}
