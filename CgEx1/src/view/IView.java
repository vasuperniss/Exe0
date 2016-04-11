package view;

import java.util.List;

import view.drawing.IDrawable;
import controller.IViewController;

public interface IView{
	void setController(IViewController controller);
	void draw(List<IDrawable> drawables);
}
