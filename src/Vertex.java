public class Vertex {

	 private String id;							//all the vertex specifications 
	 private String name;
	 private String coordinateX;
	 private String coordinateY;
	 private String vehicleTypeId;
	 private String neighborStops;

	public Vertex(String id, String name, String coordinateX, String coordinateY, String vehicleTypeId,
			String neighborStops) {

		this.id = id;
		this.name = name;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.vehicleTypeId = vehicleTypeId;
		this.neighborStops = neighborStops;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCoordinateX() {
		return coordinateX;
	}

	public String getCoordinateY() {
		return coordinateY;
	}

	public String getVehicleTypeId() {
		return vehicleTypeId;
	}

	public String getNeighborStops() {
		return neighborStops;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Vertex other = (Vertex) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
