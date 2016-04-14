package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import presenter.IPresenterView;

/**
 * The Class Canvas2D.
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public class Canvas2D extends Canvas implements IView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The presenter. */
	private IPresenterView presenter;
	
	/**
	 * Instantiates a new canvas2d.
	 *
	 * @param presenter the presenter
	 */
	public Canvas2D(IPresenterView presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void update(Graphics g) {
		//super.update(g);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		this.presenter.drawOn(g);
	}

	/* (non-Javadoc)
	 * @see view.IView#reDraw()
	 */
	@Override
	public void reDraw() {
		this.createBufferStrategy(2);
		this.repaint();
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}

	@Override
	public Frame getFrame() {
		return (Frame)this.getParent();
	}
}
