package model;

import java.awt.Graphics;

public interface IModel {

	public void draw(Graphics g);

	public void setFilled(boolean filled);

	public void addTempEdge(int x, int y, int x2, int y2);

	public void addTempEdgesToScene();
}
