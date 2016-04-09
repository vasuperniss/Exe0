package main;

import model.matrixLib.Matrix;
import model.matrixLib.Vector;

public class Main {

	public static void main(String[] args) {
		Matrix m1 = new Matrix(new float[][] {{1,2,3},{2,3,4},{3,4,5}});
		System.out.println(m1.multiply(m1));
		System.out.println(m1.add(m1));
		Vector v1 = new Vector(new float[] {5,2,7});
		System.out.println(m1.multiply(v1));
	}
}
