package model;

// only the controller should be allowed to call the set methods
// the JSP will call the "get" and "is" methods implicitly
// when the JSP specifies game.min, that gets converted to
//    a call to model.getMin()
// when the JSP specifies if(game.done), that gets converted to
//    a call to model.isDone()
public class GameTempModel {
	private String first;
	private String result;
	
	public GameTempModel() {
	}
	
	public void setFirst(String first) {
		this.first = first;
	}
	
	public String getFirst() {
		return first;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
}
