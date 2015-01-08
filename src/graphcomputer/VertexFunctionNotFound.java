package graphcomputer;

public class VertexFunctionNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public VertexFunctionNotFound() {
		super("Vertex function isn't defined. Please use GraphComputer.setVertexFunction() before execute()");
	}
}
