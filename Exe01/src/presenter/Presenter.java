package presenter;

import java.awt.Graphics;

import model.IModel;

public class Presenter implements IPresenterModel, IPresenterView {
	
	private enum State {Drawing, Stale, Fill};

	private IModel model;
	private State state;

	public Presenter(IModel model) {
		this.model = model;
	}

	@Override
	public void savePressed() {
		
	}

	@Override
	public void mouseClickedAt(int x, int y) {
		
	}

	@Override
	public void mouseMovedTo(int x, int y) {
		
	}

	@Override
	public void draw(Graphics g) {
		
	}
}
