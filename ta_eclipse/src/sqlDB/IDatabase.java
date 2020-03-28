package sqlDB;

import java.util.*;


public interface IDatabase {
	public ArrayList<String> getVerbs(String prime);
	
	public ArrayList<String> getNouns(String prime);
}
