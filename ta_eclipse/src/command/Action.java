package command;
import java.util.*;

public class Action {
	String name;
	Word verb;
	Word noun;
	ArrayList<String> altNames = new ArrayList<>();
	int method;

	public Action(String name, Word verb, Word noun, int method)
	{
		this.name = name;
		this.verb = verb;
		this.noun = noun;
		this.method = method;
	}

	public Action getAction(String name)
	{
		if(name.equals(this.name))
		{
			return this;
		}
		else
		{
			return null;
		}
	}

	public String getName()
	{
		return name;
	}
	
	public Word getVerb()
	{
		return verb;
	}
		
	public Word getNoun()
	{
		return noun;
	}

	public int getMethod()
	{
		return this.method;
	}

	public ArrayList<String> getAltNames()
	{
		return this.altNames;
	}

	public void addAltName(String alt)
	{
		this.altNames.add(alt);
		return;
	}

	public String performAction(String location)
	{
		String display = "";

		if(method == 0)	//go
		{

		}
		else if(method == 1)	//take
		{
			display = take(location);
		}
		else if (method == 2) //drop
		{
			display = drop(location);
		}

		return display;
	}



	public Action going(String direction)
	{
		return null;
	}

	public String displayAction(String command){
		return "empty";
	}

	private String go(String location)
	{
		return null;
	}

	private String take(String location)
	{
		return null;
	}

	private String drop(String location)
	{
		return null;
	}

	public static boolean checkCondition(String string) {
		// TODO Auto-generated method stub
		return false;
	}

	//private String standOn(String Location)

}
