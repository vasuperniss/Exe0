package presenter;

import java.awt.Graphics;

public interface IPresenterView {

	void savePressed();

	void mouseClickedAt(int x, int y);

	void mouseMovedTo(int x, int y);

	void draw(Graphics g);
}
