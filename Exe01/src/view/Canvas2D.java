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

	private BufferedImage canvas;
	
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
		if (this.canvas != null) {
			g.drawImage(this.canvas, 0, 0, this.canvas.getWidth(),
					this.canvas.getHeight(), null);
		}
	}

	/* (non-Javadoc)
	 * @see view.IView#reDraw()
	 */
	@Override
	public void reDraw() {
		if (canvas != null) {
			Graphics gBuffer = canvas.createGraphics();
			gBuffer.setColor(Color.WHITE);
			gBuffer.fillRect(0, 0, this.canvas.getWidth(),
					this.canvas.getHeight());
			this.presenter.drawOn(gBuffer);
		}
		this.repaint();
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.canvas = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
	}

	@Override
	public Frame getFrame() {
		return (Frame)this.getParent();
	}
}
