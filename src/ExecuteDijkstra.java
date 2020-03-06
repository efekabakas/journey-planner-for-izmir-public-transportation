import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ExecuteDijkstra {

	private List<Vertex> nodes;								//creating all the necessary lists
	private List<Edge> edges;
	private List<Line> lines;
	private List<Distance> distances;

	public void testExcute() throws FileNotFoundException, UnsupportedEncodingException {

		nodes = new ArrayList<Vertex>();				
		edges = new ArrayList<Edge>();
		lines = new ArrayList<Line>();
		distances = new ArrayList<Distance>();
 
		System.out.println();
		String startId = StartInput();						//input
		String endId = EndInput();
		
		long start = System.currentTimeMillis();			//starting time
		
		
		implementStopText(nodes);							//invoking all the necessary methods

		walkStops(nodes);

		implementDistanceText(distances);

		implementLineText(lines);
		
		implementTripText(edges);
		
		Vertex startVertex = findVertex(nodes, startId);
		
		Vertex endVertex = findVertex(nodes, endId);

		Graph graph = new Graph(nodes, edges);			     	//creating graph
		
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

		dijkstra.execute(startVertex);

		LinkedList<Vertex> path = dijkstra.getPath(endVertex);	//getting path
		

		try {													//printing path
			
			System.out.println();
			
			if (!path.isEmpty()) {

				for (Vertex vertex : path) {
					System.out.println("  " +vertex);
					
				}
				
			}
		} catch (NullPointerException e) {}
		
		 
		
		long end = System.currentTimeMillis();
		
		float difference = end - start;
		
		float time = difference/1000;							//ending and printing time
		
		System.out.println();
		
		System.out.println("  Number of Stops : " + path.size());
		
		System.out.println();
		
		System.out.println("  Time : " + time + "s");
		
		
		
	}

	public void implementStopText(List<Vertex> nodes) throws UnsupportedEncodingException, FileNotFoundException {

		File file = new File("bin\\Stop.txt");

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));

		try {

			String st;

			br.readLine(); // skipping the first line

			while ((st = br.readLine()) != null) {

				String[] splitted = st.split(";");

				Vertex node = new Vertex(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4], splitted[5]);

				nodes.add(node);
			}

		} catch (Exception FileNotFoundException) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void implementDistanceText(List<Distance> distances)
			throws UnsupportedEncodingException, FileNotFoundException {

		File file2 = new File("bin\\Distance.txt");

		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2), "UTF8"));

		try {

			String st2;

			br2.readLine(); // skipping the first line

			while ((st2 = br2.readLine()) != null) {

				String[] splitted = st2.split(";");

				Distance distance = new Distance(splitted[0], splitted[1], Integer.parseInt(splitted[2]));

				distances.add(distance);
			}

		} catch (Exception FileNotFoundException) {
			try {
				br2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void implementLineText(List<Line> lines) throws UnsupportedEncodingException, FileNotFoundException {

		File file3 = new File("bin\\Line.txt");

		BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3), "UTF8"));

		try {

			String st3;

			br3.readLine(); // skipping the first line

			while ((st3 = br3.readLine()) != null) {

				String[] splitted = st3.split(";");

				Line line = new Line(splitted[0], splitted[1], splitted[2], Integer.parseInt(splitted[3]));

				lines.add(line);
			}

		} catch (Exception FileNotFoundException) {
			try {
				br3.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void implementTripText(List<Edge> edges) throws UnsupportedEncodingException, FileNotFoundException {

		File file4 = new File("bin\\Trip.txt");

		BufferedReader br4 = new BufferedReader(new InputStreamReader(new FileInputStream(file4), "UTF8"));

		try {

			String st4;

			br4.readLine(); // skipping the first line

			while ((st4 = br4.readLine()) != null) {

				String[] splitted1 = st4.split(";");			//getting the first line 

				String next = br4.readLine();					//getting the second line

				String[] splitted2 = next.split(";");

				if (Integer.parseInt(splitted2[2]) != 1) {	    //checking order of line

					int weight = isDistanceExist(distances, splitted1[3], splitted2[3]);
					Line line = null;

					if (weight == -1)
						weight = 625;

					Vertex startVertex = findVertex(nodes, splitted1[3]);
					Vertex endVertex = findVertex(nodes, splitted2[3]);

					for (int i = 0; i < lines.size(); i++) {	

						if (lines.get(i).getLineId().equals(splitted1[0])) {
							line = lines.get(i);
							break;
						}
					}

					Edge edge = new Edge(splitted1[0], line.getLineNo(), line.getLineName(),
							Integer.parseInt(splitted1[2]), startVertex, endVertex, weight, line.getVehicleTypeId());

					edges.add(edge);

				}

			}

		} catch (Exception FileNotFoundException) {
			try {
				br4.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int isDistanceExist(List<Distance> distances, String startId, String endId) { // checks is there distance
																							// value on text file

		for (int i = 0; i < distances.size(); i++) {

			if (distances.get(i).getOriginStopId().equals(startId)
					&& distances.get(i).getDestinationId().equals(endId)) {
				return distances.get(i).getWeight();

			}
		}

		return -1;
	}

	public Vertex findVertex(List<Vertex> nodes, String id) {							//finds vertex by its id		

		for (int i = 0; i < nodes.size(); i++) {

			if (nodes.get(i).getId().equals(id)) {
				return nodes.get(i);
			}
		}
		return null;

	}

	public void walkStops(List<Vertex> nodes) {				//finding neighbor stops

		String[] splitted = null;

		for (int i = 0; i < nodes.size(); i++) {

			int weight = 0;

			String st = nodes.get(i).getNeighborStops();

			if (st != "NULL") {

				splitted = st.split("\\.");

				if (splitted.length > 1) {					//if it has more than one neighbor

					for (int j = 0; j < splitted.length; j++) {

						String[] splitted2 = splitted[j].split(":");

						Vertex v = findVertex(nodes, splitted2[0]);

						weight = Integer.parseInt(splitted2[1]);

						if (v != null) {

							Edge edge = new Edge("WALK", nodes.get(i), v, weight);

							edges.add(edge);
						}
					}
				}

				else {

					String[] splitted3 = st.split(":");					//if it has on neighbor

					Vertex v = findVertex(nodes, splitted3[0]);

					if (v != null) {
						Edge edge = new Edge("WALK", nodes.get(i), v, Integer.parseInt(splitted3[1]));
						edges.add(edge);

					}
				}
			}
		}

	}

	public String StartInput() {							//input methods
		
		System.out.print("  First Stop Id : ");
		
		Scanner scn = new Scanner(System.in);
		
		String start = scn.nextLine();
		
		return start;
		
	}
	
	public String EndInput() {
		
		
		Scanner scn = new Scanner(System.in);
		
		System.out.print("  Last  Stop Id : ");
		
		String end = scn.nextLine();
		
		return end;
	}
}