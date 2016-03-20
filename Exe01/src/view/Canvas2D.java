package view;

import java.awt.Canvas;
import java.awt.Graphics;

import presenter.IPresenterView;

public class Canvas2D extends Canvas implements IView {

	private static final long serialVersionUID = 1L;
	private IPresenterView presenter;
	
	public Canvas2D(IPresenterView presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void paint(Graphics g) {
		this.presenter.draw(g);
	}

	@Override
	public void reDraw() {
		this.repaint();
	}
}
