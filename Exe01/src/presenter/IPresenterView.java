package presenter;

import java.awt.Graphics;

import view.IView;

/**
 * The Interface IPresenterView.
 */
public interface IPresenterView {

	/**
	 * Save button pressed.
	 *
	 * @param view the view of the pressed button
	 */
	void savePressed(IView view);
	
	/**
	 * Fill button pressed.
	 *
	 * @param view the view of the pressed button
	 */
	void fillPressed(IView view);

	/**
	 * Mouse clicked at (x,y).
	 *
	 * @param view the view of the pressed button
	 * @param x the x coordinate of the click
	 * @param y the y coordinate of the click
	 */
	void mouseClickedAt(IView view, int x, int y);

	/**
	 * Mouse moved to.
	 *
	 * @param view the view of the pressed button
	 * @param x the x coordinate of the click
	 * @param y the y coordinate of the click
	 */
	void mouseMovedTo(IView view, int x, int y);

	/**
	 * Draw on.
	 *
	 * @param g the Graphics Object to draw on
	 */
	void drawOn(Graphics g);

	/**
	 * Load pressed.
	 *
	 * @param view the view of the pressed button
	 */
	void loadPressed(IView view);
}
