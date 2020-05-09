package objects;

import java.util.HashMap;

@SuppressWarnings("serial")
public class Connections extends HashMap<String, String> { // Mao of <action.name, room.ID>
	String origin; // room that the actions start in

	// performing the action in the origin
	// moves the player to the specified room id.
	public Connections() { // POJO

	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
		return;
	}

	public void addConnection(String action, String value) { // add a connection to the map
		this.put(action, value);
		return;
	}

	public String getDestination(String action) { // get the destination based on action.name
		String x = null;
		x = this.get(action);
		if (x == null) {
			return "0";
		}
		return x;
	}

	public String toString() {
		String a = "Connections: ";
		for (Entry<String, String> s : this.entrySet()) {
			a += s.toString() + '\n';
		}
		return a;
	}

}
