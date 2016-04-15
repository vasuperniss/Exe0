package model.filling;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import model.geometry2d.Edge2D;
import model.geometry2d.I2DEdge;
import model.geometry3d.I3DEdge;

/**
 * The Class ScanConversionFiller.
 * 
 * @author Michael Vassernis 319582888
 */
public class ScanConversionFiller implements IPolygonFiller {

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IPolygonFiller#fillDrawingUsingEdges(java.awt.Graphics,
	 * java.util.List)
	 */
	@Override
	public List<I2DEdge> fillDrawingUsingEdges(List<I2DEdge> edges, Color c) {
		List<I2DEdge> result = new ArrayList<I2DEdge>();
		List<I2DEdge> E = new ArrayList<>(edges);
		List<I2DEdge> A = new ArrayList<>();
		E.sort(new EdgeYMinComparetor());
		int k = yMin(E.get(0));
		IntegerComparetor intComparetor = new IntegerComparetor();
		Collection<I2DEdge> toRemove = new ArrayList<I2DEdge>();
		// scan conversion main loop
		while (!E.isEmpty() || !A.isEmpty()) {
			// from E to A list
			for (I2DEdge e : E) {
				if (yMin(e) < k) {
					A.add(e);
					toRemove.add(e);
				}
			}
			E.removeAll(toRemove);
			toRemove.clear();
			// from A to trash
			for (I2DEdge e : A) {
				if (yMax(e) < k) {
					toRemove.add(e);
				}
			}
			A.removeAll(toRemove);
			toRemove.clear();
			
			// get the all the intersection points with y = k
			List<Integer> I = new ArrayList<Integer>();
			for (I2DEdge e : A) {
				float m = e.getSlope();
				int x = (int)((k - e.getStart().getY()) / m + e.getStart().getX());
				I.add(x);
			}
			// sort the intersection points
			I.sort(intComparetor);
			for (int i = 0; i + 1 < I.size(); i+=2) {
				Edge2D e = new Edge2D(I.get(i), k, I.get(i + 1), k);
				e.setColor(c);
				result.add(e);
			}
			// smart increase to the k value
			if (A.isEmpty() && !E.isEmpty()) {
				k = Math.max(yMin(E.get(0)), k + 1);
			} else {
				k++;
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see model.filling.IPolygonFiller#fillDrawingUsing3DEdgesZeroZ(java.util.List, java.awt.Color)
	 */
	@Override
	public List<I2DEdge> fillDrawingUsing3DEdgesZeroZ(List<I3DEdge> edges, Color c) {
		List<I2DEdge> edges2d = new ArrayList<I2DEdge>();
		for (I3DEdge edge : edges) {
			edges2d.add(new Edge2D(edge));
		}
		return this.fillDrawingUsingEdges(edges2d, c);
	}

	/**
	 * Y min of the edge.
	 *
	 * @param e the edge
	 * @return the minimum y value
	 */
	private int yMin(I2DEdge e) {
		return (int) Math.min(e.getStart().getY(), e.getEnd().getY());
	}

	/**
	 * Y max of the edge.
	 *
	 * @param e the edge
	 * @return the maximum y value
	 */
	private int yMax(I2DEdge e) {
		return (int) Math.max(e.getStart().getY(), e.getEnd().getY());
	}

	/**
	 * The Class EdgeComparetor.
	 */
	private class EdgeYMinComparetor implements Comparator<I2DEdge> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(I2DEdge e1, I2DEdge e2) {
			return (int) (Math.min(e1.getStart().getY(), e1.getEnd().getY())
					- Math.min(e2.getStart().getY(), e2.getEnd().getY()));
		}
	}
	
	/**
	 * The Class IntegerComparetor.
	 */
	private class IntegerComparetor implements Comparator<Integer> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg0 - arg1;
		}
	}
}
