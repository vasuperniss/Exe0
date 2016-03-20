package presenter;

import java.awt.Graphics;

import view.IView;

public interface IPresenterView {

	void savePressed(IView view);
	
	void fillPressed(IView view);

	void mouseClickedAt(IView view, int x, int y);

	void mouseMovedTo(IView view, int x, int y);

	void draw(Graphics g);
}
