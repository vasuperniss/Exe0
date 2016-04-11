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
import model.geometry3d.Vector3D;
import model.matrixLib.Matrix;
import model.matrixLib.Vector;

public class Main {

	public static void main(String[] args) {
		Frame window = new CloseableFrame();
		
		IView view = new Canvas2D();
		IModel model = loadScene("./scene.scn", "./viewport.viw");
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

	private static void UnitTesting() {
		System.out.println("matrix operations testing.");
		Matrix m1 = new Matrix(new float[][] { { 1, 2, 3 }, { 2, 3, 4 },
				{ 3, 4, 5 } });
		System.out.println(m1.multiply(m1));
		System.out.println(m1.add(m1));
		Vector v1 = new Vector(new float[] { 5, 2, 7 });
		System.out.println(m1.multiply(v1));

		System.out.println("\ncross product testing.");
		Vector3D x = new Vector3D(2, 0, 0);
		Vector3D y = new Vector3D(0, 1, 0);
		System.out.println(x.crossProduct(y));
	}
}
