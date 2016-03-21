package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The Class ScanConversionFiller.
 * 
 * @author Michael Vassernis 319582888
 * @author
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
		E.sort(new EdgeComparetor());
		for (int k = 0; k < 600; k++) {
			List<IEdge> toRemove = new ArrayList<IEdge>();
			for (IEdge e : E) {
				if (yMin(e) <= k) {
					A.add(e);
					toRemove.add(e);
				}
			}
			for (IEdge e : toRemove) {
				E.remove(e);
			}
			
			toRemove = new ArrayList<IEdge>();
			for (IEdge e : A) {
				if (yMax(e) <= k) {
					toRemove.add(e);
				}
			}
			for (IEdge e : toRemove) {
				A.remove(e);
			}
			
			List<Integer> I = new ArrayList<Integer>();
			for (IEdge e : A) {
				float m = ((float) (e.getEnd().y - e.getStart().y))
						/ (e.getEnd().x - e.getStart().x);
				int x = (int)((k - e.getStart().y) / m) + e.getStart().x;
				I.add(x);
			}
			
			I.sort(new IntegerComparetor());
			for (int i = 0; i + 1 < I.size(); i+=2) {
				int x1 = I.get(i);
				int x2 = I.get(i + 1);
				g.drawLine(I.get(i), k, I.get(i + 1), k);
			}
		}
		int a = 5;
	}

	private int yMin(IEdge e) {
		return Math.min(e.getStart().y, e.getEnd().y);
	}

	private int yMax(IEdge e) {
		return Math.max(e.getStart().y, e.getEnd().y);
	}

	private class EdgeComparetor implements Comparator<IEdge> {

		@Override
		public int compare(IEdge e1, IEdge e2) {
			return Math.min(e1.getStart().y, e1.getEnd().y)
					- Math.min(e2.getStart().y, e2.getEnd().y);
		}

	}
	
	private class IntegerComparetor implements Comparator<Integer> {

		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg0 - arg1;
		}
	}
}
