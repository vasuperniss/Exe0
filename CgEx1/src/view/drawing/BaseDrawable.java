package view.drawing;

import java.awt.Color;
import java.awt.Graphics;

public abstract class BaseDrawable implements IDrawable {
	
	protected Color color;
	
	public BaseDrawable() {
		this.color = Color.PINK;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
	}
	
	@Override
	public void setColor(Color c) {
		this.color = c;
	}
	
	@Override
	public Color getColor() {
		return this.color;
	}
}
