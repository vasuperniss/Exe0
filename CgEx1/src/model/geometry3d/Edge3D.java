package model.geometry3d;

public class Edge3D implements I3DEdge {

	private I3DVertex start;
	private I3DVertex end;

	public Edge3D(I3DVertex start, I3DVertex end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public I3DVertex getStart() {
		return this.start;
	}

	@Override
	public I3DVertex getEnd() {
		return this.end;
	}

}
