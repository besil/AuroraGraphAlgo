package exceptions;

public class NonNegativeWeightsRequired extends RuntimeException {
	private static final long serialVersionUID = 1L;
	protected float weight;
	protected int src, dst, edgeId;
	
	public NonNegativeWeightsRequired(int edgeId, int src, int dst, float weight) {
		super("ERROR: edge "+edgeId+" has weight "+weight+" < 0. Required > 0");
		this.src = src;
		this.dst = dst;
		this.edgeId = edgeId;
		this.weight = weight;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getDst() {
		return dst;
	}

	public void setDst(int dst) {
		this.dst = dst;
	}

	public int getEdgeId() {
		return edgeId;
	}

	public void setEdgeId(int edgeId) {
		this.edgeId = edgeId;
	}
}
