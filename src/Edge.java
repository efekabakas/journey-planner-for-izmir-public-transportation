public class Edge {

	private String lineId;								//all the edge data types
	private String lineNo;
	private String lineName;
	private int order;
	private Vertex source;
	private Vertex destination; 
	private int weight;
	private int VehicleTypeId; 

	public Edge(String lineId, String lineNo, String lineName, int order, Vertex source, Vertex destination, int weight,
			int vehicleTypeId) {

		this.lineId = lineId;
		this.lineNo = lineNo;
		this.lineName = lineName;
		this.order = order;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		VehicleTypeId = vehicleTypeId;
	}

	public Edge(String lineName, Vertex source, Vertex destination, int weight) {//new constructor to implement "WALK" case.

		this.lineName = "WALK";
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public String getLineNo() {
		return lineNo;
	}

	public String getLineName() {
		return lineName;
	}

	public int getVehicleTypeId() {
		return VehicleTypeId;
	}

	public int getOrder() {
		return order;
	}

	public String getLineId() {
		return lineId;
	}

	public Vertex getDestination() {
		return destination;
	}

	public Vertex getSource() {
		return source;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return source + " " + destination;
	}

}