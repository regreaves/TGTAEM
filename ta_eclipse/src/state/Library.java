package state;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Library {
	private ArrayList<String> usernames;
	private ArrayList<String> passwords;
	private Map<String, String> credentials;
	
	// create model - test version
	public Library() {
		usernames = new ArrayList<String>();
		passwords = new ArrayList<String>();
		credentials = new TreeMap<String, String>();
		
		usernames.add("Arthur Dent");
		usernames.add("faculty");
		
		passwords.add("hitchhiker");
		passwords.add("E&CS");
		
		for (int i = 0; i < usernames.size(); i++) {
			credentials.put(usernames.get(i), passwords.get(i));
		}
	}		

	// login name - test version
	public boolean validateUserName(String name) {
		return credentials.containsKey(name);
	}

	// login credentials - test version
	public boolean validatePW(String name, String pw) {
		if (credentials.containsKey(name)) {
			if  (credentials.get(name).equals(pw)) {
				return true;
			}
		}			
		return false;
	}
}
