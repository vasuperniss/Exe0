package presenter;

import model.IModel;
import view.IView;

public class Presenter implements IPresenterModel, IPresenterView {
	
	private Object view;
	private IModel model;

	public Presenter(IView view, IModel model) {
		this.view = view;
		this.model = model;
	}
}
