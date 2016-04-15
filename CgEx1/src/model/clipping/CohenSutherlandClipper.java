package model.clipping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.geometry2d.Edge2D;
import model.geometry2d.I2DEdge;
import model.geometry2d.I2DVertex;
import model.geometry2d.Polygon2D;
import model.geometry3d.I3DEdge;

public class CohenSutherlandClipper implements ILineClipper {
	
	private static final int BELOW = 1;
	private static final int ABOVE = 2;
	private static final int RIGHT = 4;
	private static final int LEFT = 8;
	
	private float xMin;
	private float yMin;
	private float xMax;
	private float yMax;
	
	int counter = 0;

	public CohenSutherlandClipper(int xMin, int yMin, int xMax, int yMax) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}
	
	@Override
	public Polygon2D clipPolygon(Polygon2D polygon) {
		Polygon2D result = this.clipPolygonSide(
				this.clipPolygonSide(
						this.clipPolygonSide(
								this.clipPolygonSide(polygon,
										LEFT), RIGHT), ABOVE), BELOW);
		if (result != null)
			result.setColor(polygon.getColor());
		return result;
	}
	
	private Polygon2D clipPolygonSide(Polygon2D poly, int side) {
		if (poly == null) return null;
		List<I2DVertex> vertices = new ArrayList<I2DVertex>();
		List<I2DEdge> edges = poly.getEdges();
		for (I2DEdge e : edges) {
			int sCode = this.getVertexCode(e.getStart());
			int eCode = this.getVertexCode(e.getEnd());
			if ((sCode & side) != 0 && (eCode & side) != 0) {
				// nothing added
			} else if ((sCode & side) == 0 && (eCode & side) == 0) {
				vertices.add(e.getEnd());
			}
			else {
				I2DVertex intersection;
				if (side == RIGHT) {
					intersection = e.getIntVertexAtX(this.xMax);
				} else if (side == LEFT) {
					intersection = e.getIntVertexAtX(this.xMin);
				} else if (side == ABOVE) {
					intersection = e.getIntVertexAtY(this.yMax);
				} else {
					intersection = e.getIntVertexAtY(this.yMin);
				}
				if ((sCode & side) != 0) {
					vertices.add(intersection);
					vertices.add(e.getEnd());
				} else {
					vertices.add(intersection);
				}
			}
		}
		if (vertices.size() >= 2)
			return new Polygon2D(vertices);
		return null;
	}
	
	@Override
	public List<I2DEdge> clipEdges(List<I2DEdge> edges) {
		List<I2DEdge> clipedEdges = new ArrayList<I2DEdge>();
		
		for (I2DEdge e : edges) {
			counter = 0;
			I2DEdge clipedEdge = this.clipEdge(e);
			if (clipedEdge != null)
				clipedEdges.add(clipedEdge);
		}
		
		return clipedEdges;
	}
	
	@Override
	public Collection<I2DEdge> clip3DEdgesZeroZ(List<I3DEdge> edges) {
		List<I2DEdge> edges2d = new ArrayList<I2DEdge>();
		for (I3DEdge edge : edges) {
			edges2d.add(new Edge2D(edge));
		}
		return this.clipEdges(edges2d);
	}
	
	@Override
	public I2DEdge clipEdge(I2DEdge edge) {
		counter++;
		short startCode = this.getVertexCode(edge.getStart());
		short endCode = this.getVertexCode(edge.getEnd());
		if (counter > 10)
			counter = 12;
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
				intersection = edge.getIntVertexAtX(this.xMin);
			else if ((startCode & RIGHT) != 0)
				intersection = edge.getIntVertexAtX(this.xMax);
			else if ((startCode & ABOVE) != 0)
				intersection = edge.getIntVertexAtY(this.yMax);
			else if ((startCode & BELOW) != 0)
				intersection = edge.getIntVertexAtY(this.yMin);
			else if ((endCode & LEFT) != 0)
				intersection = edge.getIntVertexAtX(this.xMin);
			else if ((endCode & RIGHT) != 0)
				intersection = edge.getIntVertexAtX(this.xMax);
			else if ((endCode & ABOVE) != 0)
				intersection = edge.getIntVertexAtY(this.yMax);
			else
				intersection = edge.getIntVertexAtY(this.yMin);
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
		if (v.getY() > this.yMax) // above
			code |= ABOVE;
		else if(v.getY() < this.yMin) // below
			code |= BELOW;
		if (v.getX() > this.xMax) // right
			code |= RIGHT;
		else if (v.getX() < this.xMin) // left
			code |= LEFT;
		return code;
	}
}
