package package1;

import java.io.FileNotFoundException;

public class Dynamic {
	public static void main(String args[]) throws FileNotFoundException {
		LoadText lt = new LoadText("data/g1.txt");
		//LoadText lt = new LoadText("data/input_random_24_64.txt");
		Graph g = lt.get();
		BellmanFord d = new BellmanFord(g);
		//BellmanFord2D d = new BellmanFord2D(g);
		d.mainLoop();
		if(d.checkNegativeCycle()) {
			System.out.println("NULL, has negative cycle.");
			//System.out.println(d.shortestPath());
			//d.print();
		}
		else {
			System.out.println(d.shortestPath());
			//d.print();
		}
		//d.print();
	}
}
