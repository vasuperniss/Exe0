package view;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import controller.IViewController;

/**
 * The Class Canvas2D.
 * 
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public class Canvas2D extends BaseCanvesEventListener implements IView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private IViewController controller;

	public Canvas2D() {

	}

	@Override
	public void addController(IViewController controller) {
		this.controller = controller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		// BufferedImage img = new BufferedImage(this.getWidth(),
		// this.getHeight(), 0);
		// img.getGraphics();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char pressedKey = String.valueOf(e.getKeyChar()).toUpperCase()
				.toCharArray()[0];
		switch (pressedKey) {
		case 'C':
			// Toggle Clip on/off
			this.controller.changeClippingState();
			break;
		case 'R':
			// Resets the view and the world to the original position
			this.controller.resetToOriginalPosition();
			break;
		case 'L':
			// Load a new scene/view file according to the user selection (the file type).
			FileDialog loadDialog = new FileDialog((Frame)this.getParent(),
									"Choose a file to load from", FileDialog.LOAD);
			loadDialog.setFile("*.scn");
			loadDialog.setVisible(true);
			this.controller.loadANewFile(loadDialog.getDirectory() + loadDialog.getFile());
			break;
		case 'X':
			// Sets the X axis as the rotation axis.
			this.controller.changeRotation(IViewController.Axis.X);
			break;
		case 'Y':
			// Sets the Y axis as the rotation axis.
			this.controller.changeRotation(IViewController.Axis.Y);
			break;
		case 'Z':
			// Sets the Z axis as the rotation axis.
			this.controller.changeRotation(IViewController.Axis.Z);
			break;
		case 'F':
			// Fill the polygons (toggle).
			this.controller.changePolygonFillingState();
			break;
		case 'Q':
			// Quit.
			this.controller.quit();
			break;
		default:
			// do nothing
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int widthThird = this.getWidth() / 3;
		int heightThird = this.getHeight() / 3;
		if (widthThird <= x && x <= 2 * widthThird) {
			if (y < heightThird || y > 2 * heightThird) {
				//TODO start scaling
			} else {
				//TODO start translate
			}
		} else {
			if (heightThird <= y && y <= heightThird) {
				//TODO start scaling
			} else {
				//TODO start rotating
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//TODO update controller
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//TODO close transformation
	}
}
