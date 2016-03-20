package presenter;

import java.awt.Graphics;

import view.IView;
import model.IModel;

public class Presenter implements IPresenterModel, IPresenterView {

	private enum State {
		Drawing, Stale, Fill
	};

	private IModel model;
	private State state;
	private int vertexCounter;

	public Presenter(IModel model) {
		this.model = model;
		this.state = State.Stale;
		this.vertexCounter = 0;
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
			// redraw without fill
		} else if (this.state == State.Stale) {
			this.state = State.Fill;
			// redraw with fill
		}
	}

	@Override
	public void mouseClickedAt(IView view, int x, int y) {
		if (this.state == State.Stale) {
			this.state = State.Drawing;
			this.vertexCounter = 1;
		} else if (this.state == State.Drawing) {
			
		}
	}

	@Override
	public void mouseMovedTo(IView view, int x, int y) {
		if (this.state == State.Drawing) {
			
		}
	}

	@Override
	public void draw(Graphics g) {
		this.model.draw(g);
	}
}
