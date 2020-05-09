package command;

import java.util.ArrayList;

public class Action {
	String name; // the verb+noun combo
	Word verb;	// verb part, can include preposition
	Word noun;	// noun part, can include adjective
	ArrayList<String> altNames = new ArrayList<>(); // names constructed of synonyms that will trigger the same action
	String method;	// reference to the updater method that the action triggers

	public Action(String name, Word verb, Word noun, String method) { // constructor
		this.name = name;
		this.verb = verb;
		this.noun = noun;
		this.method = method;
	}

	public String getName() { 
		return name;
	}

	public Word getVerb() {
		return verb;
	}

	public Word getNoun() {
		return noun;
	}

	public String verb() {  // get the stringified verb
		return verb.getPrime();
	}

	public String noun() { // get the stringified noun 
		return noun.getPrime();
	}

	public String getMethod() {
		return this.method;
	}

	public ArrayList<String> getAltNames() {
		return this.altNames;
	}

	public void addAltName(String alt) {
		this.altNames.add(alt);
		return;
	}

	@Override
	public String toString() { // returns name | verb | noun | method, followed by all alt names
		String a = this.name + " | " + this.verb.getName() + " | " + this.noun.getName() + " | " + this.method + "\n";
		a += "Alt: ";
		for (String alt : this.altNames) {
			a += alt + "\n";
		}
		return a;
	}

}
