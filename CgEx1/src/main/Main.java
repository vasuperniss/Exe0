package main;

import model.geometry3d.Vector3D;
import model.matrixLib.Matrix;
import model.matrixLib.Vector;

public class Main {

	public static void main(String[] args) {
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
