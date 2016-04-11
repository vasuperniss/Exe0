package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import view.drawing.IDrawable;

public interface IModel {

	void setSceneFromFile(BufferedReader reader) throws IOException;

	void setViewportFromFile(BufferedReader reader) throws IOException;

	Viewport getViewport();

	void reset();
	
	List<IDrawable> to2DDrawing();
}
