package objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import sqlDB.DerbyDatabase;
//WORK IN PROGRESS
public class Dialogue {
	String id;
	String dialogue;
	String newickTree;
	Tree<?> tree;
	HashMap<String, String> dialogueMap;
	DerbyDatabase db;
	
	public Dialogue(){
		tree = Tree.createNewickTree(newickTree);
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
