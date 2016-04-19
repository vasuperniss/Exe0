package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import model.IModel;
import model.matrixLib.Matrix3DFactory;
import view.IView;

/**
 * The Controller class.
 *
 * @author Michael Vassernis 319582888
 */
public class Controller implements IViewController {

	/** The view. */
	private IView view;
	
	/** The model. */
	private IModel model;
	
	/** The rotation axis. */
	private Matrix3DFactory.Axis rotationAxis;
	
	/** The is filled boolean. */
	private boolean isFilled;
	
	/** The is clipped boolean. */
	private boolean isClipped;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Controller(IView view, IModel model) {
		this.view = view;
		this.model = model;
		
		// default settings
		this.rotationAxis = Matrix3DFactory.Axis.Z;
		this.isFilled = false;
		this.isClipped = false;
		// draw for the first time
		this.redraw();
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#changeClippingState()
	 */
	@Override
	public void changeClippingState() {
		this.isClipped = !this.isClipped;
		this.model.setClipped(this.isClipped);
		this.redraw();
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#resetToOriginalPosition()
	 */
	@Override
	public void resetToOriginalPosition() {
		this.model.reset();
		this.redraw();
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#loadANewFile(java.lang.String)
	 */
	@Override
	public void loadANewFile(String filepath) {
		if (filepath == null || filepath.length() <= 4) {
			// not .scn or .viw
			throw new RuntimeException("bad file given.");
		} else {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(
						new InputStreamReader(
						new FileInputStream(filepath)));
				if (filepath.endsWith(".scn"))
					// file is a scene file
					this.model.setSceneFromFile(reader);
				else if (filepath.endsWith(".viw"))
					// file is a viewport file
					this.model.setViewportFromFile(reader);
			} catch (Exception e) {
				throw new RuntimeException
							("failed to read the file given.");
			}
		}
		this.model.reset();
		this.view.reSize(this.model.getViewport().getWidth() + 40,
							this.model.getViewport().getHeight() + 40);
		this.redraw();
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#changeRotationAxis(model.matrixLib.Matrix3DFactory.Axis)
	 */
	@Override
	public void changeRotationAxis(Matrix3DFactory.Axis axis) {
		this.rotationAxis = axis;
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#changePolygonFillingState()
	 */
	@Override
	public void changePolygonFillingState() {
		this.isFilled = !this.isFilled;
		this.model.setFilled(this.isFilled);
		this.redraw();
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#quit()
	 */
	@Override
	public void quit() {
		System.exit(0);
	}
	
	/**
	 * Redraw.
	 */
	private void redraw() {
		this.view.draw(this.model.to2DDrawing());
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#setTmpScale(float)
	 */
	@Override
	public void setTmpScale(float scale) {
		this.model.setTmpScale(scale);
		this.redraw();
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#endModifing()
	 */
	@Override
	public void endModifing() {
		this.model.addModifications();
		this.redraw();
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#setTmpTransform(int, int)
	 */
	@Override
	public void setTmpTransform(int diffX, int diffY) {
		this.model.setTmpTransform(diffX, diffY);
		this.redraw();
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#setTmpRotation(float)
	 */
	@Override
	public void setTmpRotation(float deg) {
		this.model.setTmpRotation(deg, this.rotationAxis);
		this.redraw();
	}

	/* (non-Javadoc)
	 * @see controller.IViewController#sizeChanged(int, int)
	 */
	@Override
	public void sizeChanged(int width, int height) {
		this.model.setViewportSize(width, height);
		this.redraw();
	}
}
