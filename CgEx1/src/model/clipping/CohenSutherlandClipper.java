package model.clipping;

import java.util.ArrayList;
import java.util.List;

import model.geometry2d.Edge2D;
import model.geometry2d.I2DEdge;
import model.geometry2d.I2DVertex;
import model.geometry2d.Vertex2D;

public class CohenSutherlandClipper implements ILineClipper {

	private static final float EPSILON = 1 * 10 ^ -5;
	
	private static final int BELOW = 0x1;
	private static final int ABOVE = 0x10;
	private static final int RIGHT = 0x100;
	private static final int LEFT = 0x1000;
	
	private float xMin;
	private float yMin;
	private float xMax;
	private float yMax;

	public CohenSutherlandClipper(int xMin, int yMin, int xMax, int yMax) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}
	
	@Override
	public List<I2DEdge> clipEdges(List<I2DEdge> edges, Vertex2D corner,
			float width, float height) {
		List<I2DEdge> clipedEdges = new ArrayList<I2DEdge>();
		
		for (I2DEdge e : edges) {
			I2DEdge clipedEdge = this.clipEdge(e);
			if (clipedEdge != null)
				clipedEdges.add(clipedEdge);
		}
		
		return clipedEdges;
	}
	
	private I2DEdge clipEdge(I2DEdge edge) {
		short startCode = this.getVertexCode(edge.getStart());
		short endCode = this.getVertexCode(edge.getEnd());
		if ((startCode & endCode) != 0) {
			// line is fully outside
			return null;
		} else if ((startCode | endCode) == 0) {
			// line is fully inside
			return edge;
		} else {
			// get the intersection Vertex
			I2DVertex intersection;
			if ((startCode & LEFT) != 0)
				intersection = edge.getVertexAtX(this.xMin);
			else if ((startCode & RIGHT) != 0)
				intersection = edge.getVertexAtX(this.xMax);
			else if ((startCode & ABOVE) != 0)
				intersection = edge.getVertexAtY(this.yMax);
			else
				intersection = edge.getVertexAtY(this.yMin);
			
			// Recursively solve problem
			if (startCode != 0) {
				// start is outside
				return this.clipEdge(new Edge2D(intersection, edge.getEnd()));
			} else {
				// end is outside
				return this.clipEdge(new Edge2D(edge.getStart(), intersection));
			}
		}
	}
	
	private short getVertexCode(I2DVertex v)
	{
		short code = 0;
		if (v.getY() > this.yMax + EPSILON) // above
			code |= ABOVE;
		else if(v.getY() < this.yMin - EPSILON) // below
			code |= BELOW;
		if (v.getX() > this.xMax + EPSILON) // right
			code |= RIGHT;
		else if (v.getX() < this.xMin - EPSILON) // left
			code |= LEFT;
		return code;
	}
}
