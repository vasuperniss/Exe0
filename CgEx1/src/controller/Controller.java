package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import model.IModel;
import view.IView;

public class Controller implements IModelController, IViewController {

	private IView view;
	private IModel model;
	private Axis rotationAxis;

	public Controller(IView view, IModel model) {
		this.view = view;
		this.model = model;
		this.rotationAxis = Axis.X;
	}

	@Override
	public void changeClippingState() {

	}

	@Override
	public void resetToOriginalPosition() {

	}

	@Override
	public void loadANewFile(String filepath) {
		if (filepath == null || filepath.length() <= 4) {
			// do nothing
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
				// failed - do nothing
			}
		}
	}

	@Override
	public void changeRotation(Axis axis) {
		this.rotationAxis = axis;
	}

	@Override
	public void changePolygonFillingState() {

	}

	@Override
	public void quit() {
		System.exit(0);
	}
}
