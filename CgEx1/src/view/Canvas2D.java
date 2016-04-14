package view;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import model.matrixLib.Matrix3DFactory;
import view.drawing.IDrawable;
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
	
	private List<IDrawable> drawables;

	private State state = State.None;
	private int startX;
	private int startY;

	public Canvas2D() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
	}

	@Override
	public void setController(IViewController controller) {
		this.controller = controller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		if (this.drawables != null) {
			for (IDrawable d : this.drawables)
				d.draw(g);
		}
	}

	@Override
	public void draw(List<IDrawable> drawables) {
		this.drawables = drawables;
		this.repaint();
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.controller.sizeChanged(width, height);
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
			// Load a new scene/view file according to the user selection (the
			// file type).
			FileDialog loadDialog = new FileDialog((Frame) this.getParent(),
					"Choose a file to load from", FileDialog.LOAD);
			loadDialog.setFile("*.scn");
			loadDialog.setVisible(true);
			this.controller.loadANewFile(loadDialog.getDirectory()
					+ loadDialog.getFile());
			break;
		case 'X':
			// Sets the X axis as the rotation axis.
			this.controller.changeRotationAxis(Matrix3DFactory.Axis.X);
			break;
		case 'Y':
			// Sets the Y axis as the rotation axis.
			this.controller.changeRotationAxis(Matrix3DFactory.Axis.Y);
			break;
		case 'Z':
			// Sets the Z axis as the rotation axis.
			this.controller.changeRotationAxis(Matrix3DFactory.Axis.Z);
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

	enum State {
		Transforming, Rotating, Scaling, None
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.startX = e.getX();
		this.startY = this.getHeight() - e.getY();
		int widthThird = this.getWidth() / 3;
		int heightThird = this.getHeight() / 3;
		if (widthThird <= this.startX && this.startX <= 2 * widthThird) {
			if (this.startY < heightThird || this.startY > 2 * heightThird) {
				this.state = State.Scaling;
			} else {
				this.state = State.Transforming;
			}
		} else {
			if (heightThird <= this.startY && this.startY <= 2 * heightThird) {
				this.state = State.Scaling;
			} else {
				this.state = State.Rotating;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int draggedX = e.getX();
		int draggedY = this.getHeight() - e.getY();
		int diffX = draggedX - this.startX;
		int diffY = draggedY - this.startY;
		switch (this.state) {
		case Rotating:
			float startR = this.rotation(this.startX, this.startY, this.getWidth() / 2, this.getHeight() / 2);
			float currR = this.rotation(draggedX, draggedY, this.getWidth() / 2, this.getHeight() / 2);
			this.controller.setTmpRotation(-(currR - startR));
			break;
		case Transforming:
			this.controller.setTmpTransform(diffX, diffY);
			break;
		case Scaling:
			float startD = this.distance(this.startX, this.startY, this.getWidth() / 2, this.getHeight() / 2);
			float currD = this.distance(draggedX, draggedY, this.getWidth() / 2, this.getHeight() / 2);
			this.controller.setTmpScale(currD / startD);
			break;
		default:
			// do nothing
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.state = State.None;
		this.controller.endModifing();
	}
	
	private float distance(float x1, float y1, float x2, float y2) {
		return (float)Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	private float rotation(float x1, float y1, float x2, float y2) {
		float xDiff = x1 - x2;
		float yDiff = y1 - y2;
		float d = this.distance(x1, y1, x2, y2);
		if (d == 0) 
			return 0;
		float deg = (float)Math.toDegrees(Math.acos(Math.abs(xDiff / d)));
		if (xDiff < 0 && yDiff > 0)
			deg = 180 - deg;
		else if (xDiff < 0 && yDiff < 0)
			deg += 180;
		else if (xDiff > 0 && yDiff < 0)
			deg = 270 - deg;
		return deg;
	}
}
