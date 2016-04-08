package model;

import model.geometry3d.I3DVertex;
import model.geometry3d.Vertex3D;

public class Camera {
	
	public I3DVertex position;
	public I3DVertex lookAt;
	public I3DVertex up;
	
	public Camera() {
		this.position = new Vertex3D();
		this.lookAt = new Vertex3D();
		this.up = new Vertex3D();
	}
}
