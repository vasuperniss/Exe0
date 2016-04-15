package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import model.matrixLib.Matrix3DFactory;
import view.drawing.IDrawable;

/**
 * The Model interface.
 *
 * @author Michael Vassernis 319582888
 */
public interface IModel {

	/**
	 * Sets the scene from file.
	 *
	 * @param reader the new scene from file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void setSceneFromFile(BufferedReader reader) throws IOException;

	/**
	 * Sets the viewport from file.
	 *
	 * @param reader the new viewport from file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void setViewportFromFile(BufferedReader reader) throws IOException;

	/**
	 * Gets the viewport.
	 *
	 * @return the viewport
	 */
	Viewport getViewport();

	/**
	 * Reset back to original state.
	 */
	void reset();
	
	/**
	 * To 2d drawing.
	 *
	 * @return the list of items to draw
	 */
	List<IDrawable> to2DDrawing();

	/**
	 * Sets the temporary transform.
	 *
	 * @param diffX the difference in x
	 * @param diffY the difference in y
	 */
	void setTmpTransform(int diffX, int diffY);

	/**
	 * Adds the modifications.
	 */
	void addModifications();

	/**
	 * Sets the temporary scale.
	 *
	 * @param scale the new temporary scale
	 */
	void setTmpScale(float scale);

	/**
	 * Sets the temporary rotation.
	 *
	 * @param deg the degree of rotation
	 * @param axis the axis
	 */
	void setTmpRotation(float deg, Matrix3DFactory.Axis axis);

	/**
	 * Sets the filled.
	 *
	 * @param isFilled the new filling state
	 */
	void setFilled(boolean isFilled);

	/**
	 * Sets the viewport size.
	 *
	 * @param width the new width
	 * @param height the new height
	 */
	void setViewportSize(int width, int height);

	/**
	 * Sets the clipped boolean.
	 *
	 * @param isClipped the new clipping state
	 */
	void setClipped(boolean isClipped);
}
