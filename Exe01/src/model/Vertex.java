package model;

/**
 * The Class Vertex.
 * 
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public class Vertex {
	
	/** The x & y coordinates. */
	public float x,y;
	
	/**
	 * Instantiates a new vertex.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Vertex(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Instantiates a new vertex using another Vertex.
	 *
	 * @param copy the copy Vertex
	 */
	public Vertex(Vertex copy) {
		this.x = copy.x;
		this.y = copy.y;
	}
}
