package model.clipping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.geometry2d.Edge2D;
import model.geometry2d.I2DEdge;
import model.geometry2d.I2DVertex;
import model.geometry2d.Polygon2D;
import model.geometry3d.I3DEdge;

/**
 * The CohenSutherlandClipper class.
 *
 * @author Michael Vassernis 319582888
 */
public class CohenSutherlandClipper implements ILineClipper {
	
	/** The Constant BELOW. */
	private static final int BELOW = 1;
	
	/** The Constant ABOVE. */
	private static final int ABOVE = 2;
	
	/** The Constant RIGHT. */
	private static final int RIGHT = 4;
	
	/** The Constant LEFT. */
	private static final int LEFT = 8;
	
	/** The x min. */
	private float xMin;
	
	/** The y min. */
	private float yMin;
	
	/** The x max. */
	private float xMax;
	
	/** The y max. */
	private float yMax;

	/**
	 * Instantiates a new cohen sutherland clipper.
	 *
	 * @param xMin the x min
	 * @param yMin the y min
	 * @param xMax the x max
	 * @param yMax the y max
	 */
	public CohenSutherlandClipper(int xMin, int yMin, int xMax, int yMax) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}
	
	/* (non-Javadoc)
	 * @see model.clipping.ILineClipper#clipPolygon(model.geometry2d.Polygon2D)
	 */
	@Override
	public Polygon2D clipPolygon(Polygon2D polygon) {
		// clip the polygon form all sides
		Polygon2D result = this.clipPolygonSide(
				this.clipPolygonSide(
						this.clipPolygonSide(
								this.clipPolygonSide(polygon,
										LEFT), RIGHT), ABOVE), BELOW);
		if (result != null)
			// give the new polygon his original color
			result.setColor(polygon.getColor());
		return result;
	}
	
	/**
	 * Clip polygon against a side.
	 *
	 * @param poly the polygon to clip
	 * @param side the side to clip with
	 * @return the clipped polygon 2d
	 */
	private Polygon2D clipPolygonSide(Polygon2D poly, int side) {
		if (poly == null) return null;
		List<I2DVertex> vertices = new ArrayList<I2DVertex>();
		List<I2DEdge> edges = poly.getEdges();
		for (I2DEdge e : edges) {
			int sCode = this.getVertexCode(e.getStart());
			int eCode = this.getVertexCode(e.getEnd());
			if ((sCode & side) != 0 && (eCode & side) != 0) {
				// both points are outside -> nothing added
			} else if ((sCode & side) == 0 && (eCode & side) == 0) {
				// both points are inside -> add P
				vertices.add(e.getEnd());
			}
			else {
				I2DVertex intersection;
				// get the intersection point
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
					// S is outside -> add I and P
					vertices.add(intersection);
					vertices.add(e.getEnd());
				} else {
					// P is outside -> add I
					vertices.add(intersection);
				}
			}
		}
		if (vertices.size() >= 2)
			return new Polygon2D(vertices);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see model.clipping.ILineClipper#clipEdges(java.util.List)
	 */
	@Override
	public List<I2DEdge> clipEdges(List<I2DEdge> edges) {
		List<I2DEdge> clipedEdges = new ArrayList<I2DEdge>();
		
		for (I2DEdge e : edges) {
			// clips all the given edges one by one
			I2DEdge clipedEdge = this.clipEdge(e);
			if (clipedEdge != null)
				clipedEdges.add(clipedEdge);
		}
		return clipedEdges;
	}
	
	/* (non-Javadoc)
	 * @see model.clipping.ILineClipper#clip3DEdgesZeroZ(java.util.List)
	 */
	@Override
	public Collection<I2DEdge> clip3DEdgesZeroZ(List<I3DEdge> edges) {
		List<I2DEdge> edges2d = new ArrayList<I2DEdge>();
		for (I3DEdge edge : edges) {
			// change the 3d edges to 2d
			edges2d.add(new Edge2D(edge));
		}
		return this.clipEdges(edges2d);
	}
	
	/* (non-Javadoc)
	 * @see model.clipping.ILineClipper#clipEdge(model.geometry2d.I2DEdge)
	 */
	@Override
	public I2DEdge clipEdge(I2DEdge edge) {
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
	
	/**
	 * Gets the vertex code.
	 *
	 * @param v the vertex to check
	 * @return the vertex code
	 */
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
