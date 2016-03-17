package presenter;

import model.IModel;
import view.IView;

public class Presenter implements IPresenterModel, IPresenterView {
	
	private enum State {Drawing, Stale, Fill};
	
	private IView view;
	private IModel model;

	private State state;

	public Presenter(IView view, IModel model) {
		this.view = view;
		this.model = model;
	}
}
