package model.camera;

import model.geometry3d.I3DVertex;
import model.geometry3d.Vector3D;

/**
 * The OrthographicCamera class.
 *
 * @author Michael Vassernis 319582888
 */
public class PerspectiveCamera extends OrthographicCamera {

	/**
	 * Instantiates a new perspective camera.
	 *
	 * @param pos the pos
	 * @param lookAt the look at
	 * @param up the up
	 */
	public PerspectiveCamera(I3DVertex pos, I3DVertex lookAt, Vector3D up) {
		super(pos, lookAt, up);
		this.projection.setValueAt(3, 2, 1f/-20);
	}

}
