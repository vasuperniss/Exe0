package main;

import java.awt.Canvas;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import controller.Controller;
import view.Canvas2D;
import view.CloseableFrame;
import view.IView;
import model.IModel;
import model.Scene3D;

/**
 * The Main class.
 *
 * @author Michael Vassernis 319582888
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Frame window = new CloseableFrame();
		// create a new Canvas (the View)
		IView view = new Canvas2D();
		// create a new Scene from file (the Model)
		IModel model = loadScene("./vScene.scn", "./vViewport.viw");
		// create a new Controller (the Controller)
		Controller controller = new Controller(view, model);
		view.setController(controller);
		
		((Canvas)view).setSize(model.getViewport().getWidth() + 40,
								model.getViewport().getHeight() + 40);
		// show the window
		window.add((Canvas)view);
		window.pack();
		window.setVisible(true);
	}

	/**
	 * Load scene from files.
	 *
	 * @param sceneFile the scene file
	 * @param viewportFile the viewport file
	 * @return the 3d scene
	 */
	private static Scene3D loadScene(String sceneFile, String viewportFile) {
		Scene3D scene = null;
		try {
			// open the scene file
			BufferedReader sceneReader = new BufferedReader(
											new InputStreamReader(
											new FileInputStream(sceneFile)));
			// open the viewport file
			BufferedReader viewportReader = new BufferedReader(
											new InputStreamReader(
											new FileInputStream(viewportFile)));
			scene = Scene3D.fromFile(sceneReader, viewportReader);
			sceneReader.close();
			viewportReader.close();
		} catch (Exception e) {
			// failed - do nothing
		}
		return scene;
	}
}
