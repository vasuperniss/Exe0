package presenter;

import java.awt.Graphics;

public interface IPresenterView {

	void savePressed();
	
	void fillPressed();

	void mouseClickedAt(int x, int y);

	void mouseMovedTo(int x, int y);

	void draw(Graphics g);
}
