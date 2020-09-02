package package1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BellmanFord {
	int[][] A; // A[i][v], it stores the shortest path between s and v with i edges
	Graph G;
	HashMap<Integer,ArrayList<Edge>> GList; // all edges that point to v
	public BellmanFord(Graph g) {
		addS(g); // revised BellmanFord that calculates the constant shift by added an artificial source
		buildGList(); // build a table for quick edge access
		initialize();
	}
	
	private void buildGList() {
		GList = new HashMap<Integer,ArrayList<Edge>>();
		for(Edge e : G.edges) {
			int key = e.vertexTail;
			if(GList.containsKey(key)) {
				ArrayList<Edge> edges = GList.get(key); // pull out original list
				edges.add(e);
				GList.put(key, edges);
			}
			else {
				ArrayList<Edge> edges = new ArrayList<Edge>(); // create a new list
				edges.add(e);
				GList.put(key, edges);
			}
		}
	}
	
	private void addS(Graph g) {
		// add vertex S, index 0 to the original graph and link it to all vertices with length 0
		
		G = new Graph();
		G.nOfVertices = g.nOfVertices + 1; // added source S
		G.nOfEdges = g.nOfEdges; // will be updated
		
		HashSet<Integer> h = new HashSet<Integer>(); // It keeps track of what edge has been added.
		for(Edge e : g.edges) {
			G.add(e); // copy edge to G
			int vertexHead = e.vertexHead;
			int vertexTail = e.vertexTail;
			if(!h.contains(vertexHead)) { // link s to vertexHead
				Edge sv = new Edge();
				sv.length = 0;
				sv.vertexHead = 0;
				sv.vertexTail = vertexHead;
				h.add(vertexHead);
				G.add(sv);
				G.nOfEdges = G.nOfEdges + 1;
			}
			if(!h.contains(vertexTail)) { // link s to vertexTail
				Edge sv = new Edge();
				sv.length = 0;
				sv.vertexHead = 0;
				sv.vertexTail = vertexTail;
				h.add(vertexTail);
				G.add(sv);
				G.nOfEdges = G.nOfEdges + 1;
			}
		}
	}
	public void initialize() {
		A = new int[G.nOfVertices + 1][G.nOfVertices];
		for(int i = 0; i < G.nOfVertices; i++) {
			A[0][i] = 99999; // set to a large value
		}
		A[0][0] = 0; // find all path to source s
	}
	
	public void mainLoop() {
		for(int i = 1; i <= G.nOfVertices; i++) {
		//for(int i = 1; i <= 2; i++) {
			for(int v : GList.keySet()) {
				A[i][v] = Math.min(A[i - 1][v], minEdge(i - 1, v));
			}
		}
	}
	
	public int minEdge(int i, int v) {
		// find the minimum vertex w that can help the path from s to v be shorter
		
		ArrayList<Edge> edges = GList.get(v);
		//System.out.println(v);
		int min = 999999;
		for(Edge e : edges) {
			if(min > (e.length + A[i][e.vertexHead])) {
				min = e.length + A[i][e.vertexHead];
			}
		}
		return min;
	}
	
	public boolean checkNegativeCycle() {
		for(int i = 0; i < G.nOfVertices; i++) {
			if(A[G.nOfVertices][i] != A[G.nOfVertices - 1][i]) {
				return true;
			}
		}
		return false;
	}
	
	public int shortestPath() {
		int shortest = 99999;
		for(int i = 1; i < G.nOfVertices; i++) {
			if(A[G.nOfVertices][i] < shortest) {
				shortest = A[G.nOfVertices][i];
			}
		}
		return shortest;
	}
	
	public void print() {
		/*for(Edge e : GList.get(0)) {
			System.out.println("Index " + e.vertexTail + "," + e.length);
		}*/
		
		for(int i = 0; i < G.nOfVertices; i++) {
			System.out.println("Index " + i + ":" + A[0][i] + "," + A[1][i] + "," + A[2][i]);
		}
	}
}
