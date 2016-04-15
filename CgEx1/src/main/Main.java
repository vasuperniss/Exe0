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

public class Main {

	public static void main(String[] args) {
		Frame window = new CloseableFrame();
		
		IView view = new Canvas2D();
		IModel model = loadScene("./vScene.scn", "./vViewport.viw");
		Controller controller = new Controller(view, model);
		view.setController(controller);
		
		((Canvas)view).setSize(model.getViewport().getWidth() + 40,
								model.getViewport().getHeight() + 40);
		window.add((Canvas)view);
		
		window.pack();
		window.setVisible(true);
	}

	private static Scene3D loadScene(String sceneFile, String viewportFile) {
		Scene3D scene = null;
		try {
			BufferedReader sceneReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(sceneFile)));
			BufferedReader viewportReader = new BufferedReader(new InputStreamReader(
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
