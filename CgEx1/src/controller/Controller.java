package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import model.IModel;
import view.IView;

public class Controller implements IModelController, IViewController {

	private IView view;
	private IModel model;
	
	// settings
	private Axis rotationAxis;
	private boolean isFilled;
	private boolean isClipped;

	public Controller(IView view, IModel model) {
		this.view = view;
		this.model = model;
		// default settings
		this.rotationAxis = Axis.X;
		this.isFilled = false;
		this.isClipped = false;
	}

	@Override
	public void changeClippingState() {
		this.isClipped = !this.isClipped;
	}

	@Override
	public void resetToOriginalPosition() {
		this.model.reset();
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
	public void changeRotationAxis(Axis axis) {
		this.rotationAxis = axis;
	}

	@Override
	public void changePolygonFillingState() {
		this.isFilled = !this.isFilled;
		
		this.view.draw(this.model.to2DDrawing());
	}

	@Override
	public void quit() {
		System.exit(0);
	}
}
