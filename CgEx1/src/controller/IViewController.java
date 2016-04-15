package controller;

import model.matrixLib.Matrix3DFactory;

/**
 * The View Controller interface.
 *
 * @author Michael Vassernis 319582888
 */
public interface IViewController {

	/**
	 * Change clipping state.
	 */
	void changeClippingState();

	/**
	 * Reset the Scene to it's original state.
	 */
	void resetToOriginalPosition();

	/**
	 * Load a new scene of viewport.
	 *
	 * @param file the file to load from
	 */
	void loadANewFile(String file);

	/**
	 * Change rotation axis.
	 *
	 * @param axis the new axis
	 */
	void changeRotationAxis(Matrix3DFactory.Axis axis);

	/**
	 * Change polygon filling state.
	 */
	void changePolygonFillingState();

	/**
	 * Quit.
	 */
	void quit();

	/**
	 * Sets the temporary scale.
	 *
	 * @param f the new temporary scale
	 */
	void setTmpScale(float f);

	/**
	 * End modifying.
	 */
	void endModifing();

	/**
	 * Sets the temporary transform.
	 *
	 * @param diffX the temporary difference in x
	 * @param diffY the temporary difference in y
	 */
	void setTmpTransform(int diffX, int diffY);

	/**
	 * Sets the temporary rotation.
	 *
	 * @param deg the new temporary rotation in degrees
	 */
	void setTmpRotation(float deg);

	/**
	 * Size changed.
	 *
	 * @param width the new width
	 * @param height the new height
	 */
	void sizeChanged(int width, int height);

}
