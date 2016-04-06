package model;

import controller.IModelController;

public class Scene implements IModel {

	private IModelController controller;

	@Override
	public void addController(IModelController controller) {
		this.controller = controller;
	}

}
