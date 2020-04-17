package objects;

import java.util.HashMap;
import command.Action;

@SuppressWarnings("serial")
public class Connections extends HashMap<String, String> {
	String origin;
	
	public Connections() {
		
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
		return;
	}
	
	public void addConnection(String action, String value) {
		this.put(action, value);
		return;
	}
	
	public String getDestination(String action) {
		String x = null;
		System.out.println("Action: " + action);
		System.out.println("Connections: " + this);
		x = this.get(action);
		if(x == null) {
			return "0";
		}
		return x;
	}
	
	public String toString() {
		String a = "";
		for (Entry<String, String> s : this.entrySet()) {
			a += s.toString() + '\n';
		}
		return a;
	}
	
}
