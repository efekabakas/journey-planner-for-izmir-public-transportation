
public class Line {

	private String lineId;								//some attributes to receiving data from line text
	private String lineNo;
	private String lineName;
	private int vehicleTypeId;

	public Line(String lineId, String lineNo, String lineName, int vehicleTypeId) {

		this.lineId = lineId;  
		this.lineNo = lineNo;
		this.lineName = lineName;
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getLineId() {
		return lineId;
	}

	public String getLineNo() {
		return lineNo;
	}

	public String getLineName() {
		return lineName;
	}

	public int getVehicleTypeId() {
		return vehicleTypeId;
	}

}
