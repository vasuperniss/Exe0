package model;

import java.io.BufferedReader;
import java.io.IOException;

import controller.IModelController;

public interface IModel {

	void setController(IModelController controller);

	void setSceneFromFile(BufferedReader reader) throws IOException;

	void setViewportFromFile(BufferedReader reader) throws IOException;
}
