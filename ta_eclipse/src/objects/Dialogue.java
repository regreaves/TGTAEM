package objects;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dialogue {
	//Unique ID for each line of dialogue
	String id;
	//The actual dialogue string
	String dialogue;
	//Newick tree that gets parsed into a tree
	String newickTree;
	//List of newick tree strings, this is not used
	ArrayList<String> newickTreeList = new ArrayList<String>();
	//List of all trees with strings, used after iterating a tree
	ArrayList<Tree<String>> treeList = new ArrayList<Tree<String>>();
	//List of all dialogue strings
	ArrayList<String> dialogueList = new ArrayList<String>();
	
	public Dialogue(){
		
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
	
	//Takes newick tree string, creates a tree with it, iterates the tree into a list, pulls all the strings from the list
	public void parseNewickTree(String newickTree) {
		Tree<String> t = Tree.createNewickTree(newickTree);
		this.setTreeList(Tree.iterateTree(t));
		for(int i = 0; i < this.treeList.size(); i++) {
			this.dialogueList.add(this.treeList.get(i).data);
		}
		return;
	}
	
	public void setTreeList(ArrayList<Tree<String>> treeList){
		this.treeList = treeList;
		return;
	}
	
	public ArrayList<Tree<String>> getTreeList(){
		return this.treeList;
	}
	
	
	public void setDialogueList(ArrayList<String> dialogueList){
		this.dialogueList = dialogueList;
		return;
	}
	
	public ArrayList<String> getDialogueList(){
		return this.dialogueList;
	}
	
	//This is not used
	public String getDialogueFromId(String id){
		if(this.id.equals(id)) {
			return this.dialogue;
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException, SQLException {

	}
}
