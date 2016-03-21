package presenter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import view.IView;
import model.IModel;

/**
 * The Class Presenter.
 */
public class Presenter implements IPresenterView {

	/**
	 * The Enum State.
	 */
	private enum State {

		/** Drawing state. */
		Drawing,
		/** Stale state. */
		Stale,
		/** Fill state. */
		Fill
	};

	/** The model. */
	private IModel model;

	/** The state. */
	private State state;

	/** The vertex counter for building new Polygons. */
	private int vertexCounter;

	/** The temporary vertex. */
	private Point tempVertex;

	/** The future vertex. */
	private Point futureVertex;

	/** The start point. */
	private Point startVertex;

	/**
	 * Instantiates a new presenter.
	 *
	 * @param model the model
	 */
	public Presenter(IModel model) {
		this.model = model;
		this.state = State.Stale;
		this.vertexCounter = 0;
		this.tempVertex = null;
		this.futureVertex = null;
		this.startVertex = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presenter.IPresenterView#savePressed(view.IView)
	 */
	@Override
	public void savePressed(IView view) {
		if (this.state != State.Drawing) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presenter.IPresenterView#fillPressed(view.IView)
	 */
	@Override
	public void fillPressed(IView view) {
		if (this.state == State.Fill) {
			this.state = State.Stale;

		} else if (this.state == State.Stale) {
			this.state = State.Fill;

		}
		view.reDraw();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presenter.IPresenterView#mouseClickedAt(view.IView, int, int)
	 */
	@Override
	public void mouseClickedAt(IView view, int x, int y) {
		if (this.state == State.Stale) {
			this.state = State.Drawing;
			this.vertexCounter = 1;
			this.startVertex = new Point(x, y);
			this.tempVertex = new Point(x, y);
			this.futureVertex = new Point(x, y);
		} else if (this.state == State.Drawing) {
			if (startVertex.distance(x, y) <= 5 && this.vertexCounter >= 3) {
				this.model.addTempEdgesToScene();
				this.state = State.Stale;
			} else {
				this.model.addTempEdge(this.tempVertex.x, this.tempVertex.y, x,
						y);
				this.vertexCounter++;
				this.tempVertex = new Point(x, y);
				this.futureVertex = new Point(x, y);
			}
		}
		view.reDraw();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presenter.IPresenterView#mouseMovedTo(view.IView, int, int)
	 */
	@Override
	public void mouseMovedTo(IView view, int x, int y) {
		if (this.state == State.Drawing) {
			if (startVertex.distance(x, y) <= 5 && this.vertexCounter >= 3) {
				this.futureVertex = this.startVertex;
			} else {
				this.futureVertex = new Point(x, y);
			}
			view.reDraw();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presenter.IPresenterView#drawOn(java.awt.Graphics)
	 */
	@Override
	public void drawOn(Graphics g) {
		this.model.draw(g);
		if (this.state == State.Drawing && this.tempVertex != null
				&& this.futureVertex != null) {
			g.setColor(Color.PINK);
			g.drawLine(this.tempVertex.x, this.tempVertex.y,
					this.futureVertex.x, this.futureVertex.y);
		}
	}
}
