package model.geometry3d;

import view.drawing.IDrawable;

public interface I3DEdge extends IDrawable {

	/**
	 * Gets the start Vertex of the Edge.
	 *
	 * @return the start Vertex
	 */
	public I3DVertex getStart();
	
	/**
	 * Gets the end Vertex of the Edge.
	 *
	 * @return the end Vertex
	 */
	public I3DVertex getEnd();
}
