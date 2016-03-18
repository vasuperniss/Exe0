package model;

import java.awt.Graphics;
import java.awt.Point;

public interface Edge {

	public Point getStart();
	
	public Point getEnd();

	public void draw(Graphics g);
}
