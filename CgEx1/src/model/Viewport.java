package model;

import java.io.BufferedReader;
import java.io.IOException;

import model.geometry3d.Vertex3D;

public class Viewport {

	private Camera camera;
	private int width, height;
	
	private Viewport() {
		this.camera = new Camera();
		this.width = 0;
		this.height = 0;
	}
	
	public static Viewport fromFile(BufferedReader viewportFileReader) throws IOException {
		Viewport returned = new Viewport();
		String line;
		while ((line = viewportFileReader.readLine()) != null) {
			String[] args = line.split(" ");
			if (args[0].compareToIgnoreCase("_Position_") == 0) {
				returned.camera.position = new Vertex3D(Integer.parseInt(args[1]),
														Integer.parseInt(args[2]),
														Integer.parseInt(args[3]));
			} else if (args[0].compareToIgnoreCase("_LookAt_") == 0) {
				returned.camera.lookAt = new Vertex3D(Integer.parseInt(args[1]),
														Integer.parseInt(args[2]),
														Integer.parseInt(args[3]));
			} else if (args[0].compareToIgnoreCase("_Up_") == 0) {
				returned.camera.up = new Vertex3D(Integer.parseInt(args[1]),
														Integer.parseInt(args[2]),
														Integer.parseInt(args[3]));
			} else if (args[0].compareToIgnoreCase("_Window_") == 0) {
				
			} else if (args[0].compareToIgnoreCase("_Viewport_") == 0) {
				returned.width = Integer.parseInt(args[1]);
				returned.height = Integer.parseInt(args[2]);
			}
		}
		return returned;
	}
}
