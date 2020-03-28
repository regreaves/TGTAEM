package sqlDB;

import java.util.*;
import command.Action;

public interface IDatabase {
	public ArrayList<String> getVerbs(String prime);
	
	public ArrayList<String> getNouns(String prime);
	
	public ArrayList<Action> getActions();
}
