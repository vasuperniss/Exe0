package model.camera;

import model.geometry3d.I3DVertex;
import model.geometry3d.Vector3D;

public class PerspectiveCamera extends OrthographicCamera {

	public PerspectiveCamera(I3DVertex pos, I3DVertex lookAt, Vector3D up) {
		super(pos, lookAt, up);
		this.projection.setValueAt(3, 2, 1f/-20);
	}

}
