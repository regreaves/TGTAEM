package objects;

import java.util.HashMap;
import command.Action;

@SuppressWarnings("serial")
public class Connections extends HashMap<Action, String> {
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
	
	public void addConnection(Action action, String value) {
		this.put(action, value);
		return;
	}
	
	public String getDestination(Action action) {
		String x = null;
		x = this.get(action);
		if(x == null) {
			return "0";
		}
		return x;
	}
	
//	public static void main() {
//		Connections c = new Connections();
//		//TODO ???
//		String s = c.toString();
//		System.out.print(s);
//	}
//	
//	@Override
//	public String toString() {
//		String a = "";
//		for (Entry<Action, String> s : this.entrySet()) {
//			a += s.toString();
//		}
//		return a;
//	}
	
}
