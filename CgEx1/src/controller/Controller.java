package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import model.IModel;
import model.matrixLib.Matrix3DFactory;
import view.IView;

public class Controller implements IViewController {

	private IView view;
	private IModel model;
	
	// settings
	private Matrix3DFactory.Axis rotationAxis;
	private boolean isFilled;
	private boolean isClipped;

	public Controller(IView view, IModel model) {
		this.view = view;
		this.model = model;
		// default settings
		this.rotationAxis = Matrix3DFactory.Axis.X;
		this.isFilled = false;
		this.isClipped = false;
		
		this.redraw();
	}

	@Override
	public void changeClippingState() {
		this.isClipped = !this.isClipped;
		//TODO:: change clipping state
		this.redraw();
	}

	@Override
	public void resetToOriginalPosition() {
		this.model.reset();
		this.redraw();
	}

	@Override
	public void loadANewFile(String filepath) {
		if (filepath == null || filepath.length() <= 4) {
			throw new RuntimeException("bad file given.");
		} else {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(
						new FileInputStream(filepath)));
				if (filepath.endsWith(".scn"))
					this.model.setSceneFromFile(reader);
				else if (filepath.endsWith(".viw"))
					this.model.setViewportFromFile(reader);
			} catch (Exception e) {
				throw new RuntimeException("failed to read the file given.");
			}
		}
	}

	@Override
	public void changeRotationAxis(Matrix3DFactory.Axis axis) {
		this.rotationAxis = axis;
	}

	@Override
	public void changePolygonFillingState() {
		this.isFilled = !this.isFilled;
		this.model.setFilled(this.isFilled);
		this.redraw();
	}

	@Override
	public void quit() {
		System.exit(0);
	}
	
	private void redraw() {
		this.view.draw(this.model.to2DDrawing());
	}

	@Override
	public void setTmpScale(float scale) {
		this.model.setTmpScale(scale);
		this.redraw();
	}

	@Override
	public void endModifing() {
		this.model.addModifications();
		this.redraw();
	}

	@Override
	public void setTmpTransform(int diffX, int diffY) {
		this.model.setTmpTransform(diffX, diffY);
		this.redraw();
	}

	@Override
	public void setTmpRotation(float deg) {
		this.model.setTmpRotation(deg, this.rotationAxis);
		this.redraw();
	}

	@Override
	public void sizeChanged(int width, int height) {
		this.model.setViewportSize(width, height);
	}
}
