package objects;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import sqlDB.DatabaseProvider;
import sqlDB.DerbyDatabase;
//WORK IN PROGRESS
public class Dialogue {
	String id;
	String dialogue;
	String newickTree;
	ArrayList<String> newickTreeList;
	ArrayList<Tree<String>> tree;
	HashMap<String, String> dialogueMap;
	DerbyDatabase db;
	
	public Dialogue() throws SQLException{
		for(int i = 0; i < newickTreeList.size(); i++) {
			newickTree = newickTreeList.get(i);
			tree.add(i, Tree.createNewickTree(newickTree));
			for(int j = 0; j < tree.size(); j++) {
				id = tree.get(j).data;
				if(id == db.getDialogue().get(j).getID()) {
					dialogue = db.getDialogue().get(j).getDialogue();
					dialogueMap.put(id, dialogue);
				}
			}
		} 
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
		Tree.createNewickTree(newickTree);
		return;
	}
	
	public String getNewickTree(){
		return this.newickTree;
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		DatabaseProvider.setInstance(new DerbyDatabase());
		DerbyDatabase db = DatabaseProvider.getInstance();
		Dialogue d = new Dialogue(db);
		System.out.println(d.getDialogue());
	}
}
