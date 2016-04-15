package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import model.matrixLib.Matrix3DFactory;
import view.drawing.IDrawable;

public interface IModel {

	void setSceneFromFile(BufferedReader reader) throws IOException;

	void setViewportFromFile(BufferedReader reader) throws IOException;

	Viewport getViewport();

	void reset();
	
	List<IDrawable> to2DDrawing();

	void setTmpTransform(int diffX, int diffY);

	void addModifications();

	void setTmpScale(float scale);

	void setTmpRotation(float deg, Matrix3DFactory.Axis axis);

	void setFilled(boolean isFilled);

	void setViewportSize(int width, int height);

	void setClipped(boolean isClipped);
}
