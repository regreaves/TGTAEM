package objects;

import java.util.HashMap;
//WORK IN PROGRESS
public class Dialogue {
	String id;
	String dialogue;
	String newickTree;
	Tree<?> tree;
	HashMap<String, String> dialogueMap;
	
	public Dialogue() {
		
	}
	
	public void setID(String id){
		this.id = id;
		return;
	}
	
	public String getID(){
		return this.id;
	}
	
	public void setDialogue(String dialogue){
		this.dialogue = dialogue;
		return;
	}
	
	public String getDialogue(){
		return this.dialogue;
	}
	
	public void setNewickTree(String newickTree){
		this.newickTree = newickTree;
		return;
	}
	
	public String getNewickTree(){
		return this.newickTree;
	}

}
