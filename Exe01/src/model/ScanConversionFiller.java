package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * The Class ScanConversionFiller.
 * 
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public class ScanConversionFiller implements IPolygonFiller {

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IPolygonFiller#fillDrawingUsingEdges(java.awt.Graphics,
	 * java.util.List)
	 */
	@Override
	public void fillDrawingUsingEdges(Graphics g, List<IEdge> edges) {
		List<IEdge> E = new ArrayList<>(edges);
		List<IEdge> A = new ArrayList<>();
		E.sort(new EdgeYMinComparetor());
		int k = yMin(E.get(0));
		IntegerComparetor intComparetor = new IntegerComparetor();
		Collection<IEdge> toRemove = new ArrayList<IEdge>();
		// scan conversion main loop
		while (!E.isEmpty() || !A.isEmpty()) {
			// from E to A list
			for (IEdge e : E) {
				if (yMin(e) <= k) {
					A.add(e);
					toRemove.add(e);
				}
			}
			E.removeAll(toRemove);
			toRemove.clear();
			// from A to trash
			for (IEdge e : A) {
				if (yMax(e) <= k) {
					toRemove.add(e);
				}
			}
			A.removeAll(toRemove);
			toRemove.clear();
			
			// get the all the intersection points with y = k
			List<Integer> I = new ArrayList<Integer>();
			for (IEdge e : A) {
				float m = e.getSlope();
				int x = (int)((k - e.getStart().y) / m + e.getStart().x);
				I.add(x);
			}
			// sort the intersection points
			I.sort(intComparetor);
			for (int i = 0; i + 1 < I.size(); i+=2) {
				g.drawLine(I.get(i), k, I.get(i + 1), k);
			}
			// smart increase to the k value
			if (A.isEmpty() && !E.isEmpty()) {
				k = yMin(E.get(0));
			} else {
				k++;
			}
		}
	}

	/**
	 * Y min of the edge.
	 *
	 * @param e the edge
	 * @return the minimum y value
	 */
	private int yMin(IEdge e) {
		return (int) Math.min(e.getStart().y, e.getEnd().y);
	}

	/**
	 * Y max of the edge.
	 *
	 * @param e the edge
	 * @return the maximum y value
	 */
	private int yMax(IEdge e) {
		return (int) Math.max(e.getStart().y, e.getEnd().y);
	}

	/**
	 * The Class EdgeComparetor.
	 */
	private class EdgeYMinComparetor implements Comparator<IEdge> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(IEdge e1, IEdge e2) {
			return (int) (Math.min(e1.getStart().y, e1.getEnd().y)
					- Math.min(e2.getStart().y, e2.getEnd().y));
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
