package package1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadText {
	String fileToLoad;
	int length = 0;
	// First line of the file stores the number of vertexes from the test file
	public LoadText(String fileName) throws FileNotFoundException {
		fileToLoad = fileName;
		System.out.println(fileName);
		size();
	}

	public void size() throws FileNotFoundException {
		int size = 0;
		File myObj = new File(fileToLoad);
		Scanner myReader = new Scanner(myObj);
		while(myReader.hasNextLine()) {
			size ++;
			myReader.nextLine();
			//System.out.println("Length of Input: " + size);
		}
		//myReader.close();
		length = size;
		System.out.println("Length of Input: " + size);
	}

	public Graph get() throws FileNotFoundException {
		Graph initData = new Graph();
		File myObj = new File(fileToLoad);
		Scanner myReader = new Scanner(myObj);
		int index = 0;
		while(myReader.hasNextLine()) {
			String data = myReader.nextLine().strip();
			String[] values = data.split("\\s");
			if(index < 1) {
				initData.nOfEdges = Integer.parseInt(values[1]);
				initData.nOfVertices = Integer.parseInt(values[0]);
				index ++;
				continue; // starting line
			}
			Edge e = new Edge();
			e.vertexHead = Integer.parseInt(values[0]);
			e.vertexTail = Integer.parseInt(values[1]);
			e.length = Integer.parseInt(values[2]);
			initData.add(e);
		}
		myReader.close();
		return initData;
	}
}