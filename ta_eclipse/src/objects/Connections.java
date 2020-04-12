package objects;

import java.util.HashMap;
import command.Action;

@SuppressWarnings("serial")
public class Connections extends HashMap<Action, String> {
	String room;
	
	public Connections() {
		
	}
	
	public String getRoomOrigin() {
		return room;
	}
	
	public void setRoomOrigin(String room) {
		this.room = room;
		return;
	}
	
	public void addConnection(Action action, String value) {
		this.put(action, value);
		return;
	}
	
	public String getRoomDestination(Action action) {
		String x = null;
		x = this.get(action);
		if(x == null) {
			return "0";
		}
		return x;
	}
}
