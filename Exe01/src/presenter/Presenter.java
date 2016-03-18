package presenter;

import model.IModel;
import view.IView;

public class Presenter implements IPresenterModel, IPresenterView {
	
	private enum State {Drawing, Stale, Fill};

	private State state;

	public Presenter() {
		
	}

	@Override
	public void savePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClickedAt(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMovedTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
