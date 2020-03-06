
public class Distance {

	private String originStopId;			//some attributes to receiving data from distance text
	private String destinationId;
	private int weight;

	public Distance(String originStopId, String destinationId, int weight) {
 
		this.originStopId = originStopId;
		this.destinationId = destinationId;
		this.weight = weight;
	}

	public String getOriginStopId() {
		return originStopId;
	}

	public String getDestinationId() {
		return destinationId;
	}

	public int getWeight() {
		return weight;
	}

}
