import java.util.*;

public class Action {
	String name;
	Word verb;
	Word noun;
	int method;

	public Action(String name, Word verb, Word noun, int method)
	{
		this.name = name;
		this.verb = verb;
		this.noun = noun;
		this.method = method;
	}

	public String performAction()
	{
		if(method == 1)	//take
		{

		}
		else if (method == 2) //drop
		{

		}
	}

	public String displayAction(String command){
		return "empty";
	}

	private String take(String location)
	{

	}

	private String drop(String location)
	{

	}

	private String standOn(String Location)
	{

	}
}
