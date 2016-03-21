package view;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;

import presenter.IPresenterView;

/**
 * The Class Canvas2D.
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
	
	/* (non-Javadoc)
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		this.presenter.drawOn(g);
	}

	/* (non-Javadoc)
	 * @see view.IView#reDraw()
	 */
	@Override
	public void reDraw() {
		this.repaint();
	}

	@Override
	public Frame getFrame() {
		return (Frame)this.getParent();
	}
}
