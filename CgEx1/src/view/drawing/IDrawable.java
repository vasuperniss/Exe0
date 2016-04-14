package view.drawing;

import java.awt.Color;
import java.awt.Graphics;

public interface IDrawable {
	void draw(Graphics g);
	void setColor(Color c);
	Color getColor();
}
