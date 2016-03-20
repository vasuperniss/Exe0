package presenter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import view.IView;
import model.IModel;

public class Presenter implements IPresenterModel, IPresenterView {

	private enum State {
		Drawing, Stale, Fill
	};

	private IModel model;
	private State state;

	private int vertexCounter;
	private Point tempVertex;
	private Point furtureVertex;
	private Point startPoint;

	public Presenter(IModel model) {
		this.model = model;
		this.state = State.Stale;
		this.vertexCounter = 0;
		this.tempVertex = null;
		this.furtureVertex = null;
		this.startPoint = null;
	}

	@Override
	public void savePressed(IView view) {
		if (this.state != State.Drawing) {

		}
	}

	@Override
	public void fillPressed(IView view) {
		if (this.state == State.Fill) {
			this.state = State.Stale;

		} else if (this.state == State.Stale) {
			this.state = State.Fill;

		}
		view.reDraw();
	}

	@Override
	public void mouseClickedAt(IView view, int x, int y) {
		if (this.state == State.Stale) {
			this.state = State.Drawing;
			this.vertexCounter = 1;
			this.startPoint = new Point(x, y);
			this.tempVertex = new Point(x, y);
			this.furtureVertex = new Point(x, y);
		} else if (this.state == State.Drawing) {
			if (startPoint.distance(x, y) <= 5 && this.vertexCounter >= 3) {
				this.model.addTempEdgesToScene();
				this.state = State.Stale;
			} else {
				this.model.addTempEdge(this.tempVertex.x, this.tempVertex.y,
									x, y);
				this.vertexCounter++;
				this.tempVertex = new Point(x, y);
				this.furtureVertex = new Point(x, y);
			}
		}
		view.reDraw();
	}

	@Override
	public void mouseMovedTo(IView view, int x, int y) {
		if (this.state == State.Drawing) {
			if (startPoint.distance(x, y) <= 5 && this.vertexCounter >= 3) {
				this.furtureVertex = this.startPoint;
			} else {
				this.furtureVertex = new Point(x, y);
			}
			view.reDraw();
		}
	}

	@Override
	public void draw(Graphics g) {
		this.model.draw(g);
		if (this.state == State.Drawing && this.tempVertex != null
				&& this.furtureVertex != null) {
			g.setColor(Color.PINK);
			g.drawLine(this.tempVertex.x, this.tempVertex.y,
					this.furtureVertex.x, this.furtureVertex.y);
		}
	}
}
