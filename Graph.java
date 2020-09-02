package package1;

import java.util.ArrayList;

public class Graph {
	ArrayList<Edge> edges;
	int nOfEdges;
	int nOfVertices;
	public Graph() {
		edges = new ArrayList<Edge>();
		nOfEdges = 0;
		nOfVertices = 0;
	}
	public void add(Edge e) {
		edges.add(e);
	}
}