package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;

import model.matrixLib.Matrix3DFactory;
import view.drawing.IDrawable;
import controller.IViewController;

/**
 * The Class Canvas2D.
 * 
 * @author Michael Vassernis 319582888
 */
public class Canvas2D extends BaseCanvesEventListener implements IView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The controller. */
	private IViewController controller;
	
	/** The drawables. */
	private List<IDrawable> drawables;

	/** The state of the user interaction. */
	private State state = State.None;
	
	/** The clicked start x position. */
	private int startX;
	
	/** The clicked start y position. */
	private int startY;

	/**
	 * Instantiates a new canvas 2d.
	 */
	public Canvas2D() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		this.addComponentListener(this);
	}

	/* (non-Javadoc)
	 * @see view.IView#setController(controller.IViewController)
	 */
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

	/* (non-Javadoc)
	 * @see view.IView#draw(java.util.List)
	 */
	@Override
	public void draw(List<IDrawable> drawables) {
		this.drawables = drawables;
		this.repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#setSize(int, int)
	 */
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.controller.sizeChanged(width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
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
			JFileChooser fileChooser = new JFileChooser(Paths.get("").toAbsolutePath().toString());
		    if (fileChooser.showOpenDialog(this.getParent()) == JFileChooser.APPROVE_OPTION) {
		        File selectedFile = fileChooser.getSelectedFile();
				this.controller.loadANewFile(selectedFile.getAbsolutePath());
		    }
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

	/**
	 * The Enum State.
	 */
	enum State {
		/** The Transforming. */
		Transforming, 
		 /** The Rotating. */
		 Rotating, 
		 /** The Scaling. */
		 Scaling, 
		 /** The None. */
		 None
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		this.startX = e.getX();
		this.startY = this.getHeight() - e.getY();
		int widthThird = this.getWidth() / 3;
		int heightThird = this.getHeight() / 3;
		if (widthThird <= this.startX && this.startX <= 2 * widthThird) {
			if (this.startY < heightThird || this.startY > 2 * heightThird) {
				// scaling section clicked
				this.state = State.Scaling;
			} else {
				// transforming section clicked
				this.state = State.Transforming;
			}
		} else {
			if (heightThird <= this.startY && this.startY <= 2 * heightThird) {
				// scaling section clicked
				this.state = State.Scaling;
			} else {
				// rotation section clicked
				this.state = State.Rotating;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		int draggedX = e.getX();
		int draggedY = this.getHeight() - e.getY();
		int diffX = draggedX - this.startX;
		int diffY = draggedY - this.startY;
		switch (this.state) {
		case Rotating:
			// calculate the start and current relative degrees
			float startR = this.rotation(this.startX, this.startY,
												this.getWidth() / 2, this.getHeight() / 2);
			float currR = this.rotation(draggedX, draggedY,
												this.getWidth() / 2, this.getHeight() / 2);
			// make a temporary rotation
			this.controller.setTmpRotation(-(currR - startR));
			break;
		case Transforming:
			// make a temporary moving
			this.controller.setTmpTransform(diffX, diffY);
			break;
		case Scaling:
			// calculate the start and current  distances from middle
			float startD = Math.max(this.distance(this.startX, this.startY,
							this.getWidth() / 2, this.getHeight() / 2), 1);
			float currD = Math.max(this.distance(draggedX, draggedY,
							this.getWidth() / 2, this.getHeight() / 2), 1);
			// make a temporary scale
			this.controller.setTmpScale(currD / startD);
			break;
		default:
			// do nothing
			break;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		this.state = State.None;
		this.controller.endModifing();
	}
	
	/**
	 * Distance.
	 *
	 * @param x1 the x1
	 * @param y1 the y1
	 * @param x2 the x2
	 * @param y2 the y2
	 * @return the float
	 */
	private float distance(float x1, float y1, float x2, float y2) {
		return (float)Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	/**
	 * Rotation.
	 *
	 * @param x1 the x1
	 * @param y1 the y1
	 * @param x2 the x2
	 * @param y2 the y2
	 * @return the float
	 */
	private float rotation(float x1, float y1, float x2, float y2) {
		float xDiff = x1 - x2;
		float yDiff = y1 - y2;
		float d = this.distance(x1, y1, x2, y2);
		if (d == 0) 
			return 0;
		float deg = (float)Math.toDegrees(Math.acos(Math.abs(xDiff / d)));
		// fix the degree according to the section on the R^2 axis system
		if (xDiff < 0 && yDiff > 0)
			deg = 180 - deg;
		else if (xDiff < 0 && yDiff < 0)
			deg += 180;
		else if (xDiff > 0 && yDiff < 0)
			deg = 360 - deg;
		return deg;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		this.setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
	}

	@Override
	public void reSize(int width, int height) {
		this.getParent().setSize(width, height);
		this.setSize(width, height);
	}
}
