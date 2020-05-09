package objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Tree class is partially based on code found at https://www.cs.dartmouth.edu/~cbk/classes/10/15spring/notes/7/code/BinaryTree.java
//@author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
public class Tree<T> {
	//Binary tree, left and right nodes are simply trees in themselves
	private Tree<T> left, right;
	//Generic data type but we only use strings as the Tree class is only used for dialogue handling
	T data;
	

	//Leaf node
    public Tree(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
    
    //Inner node
    public Tree(T data, Tree<T> left, Tree<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    
    public void setLeft(Tree<T> left){
    	this.left = left;
    	return;
    }
    
    public Tree<T> getLeft(){
    	return this.left;
    }
    
    public void setRight(Tree<T> right){
    	this.right = right;
    	return;
    }
    
    public Tree<T> getRight(){
    	return this.right;
    }
 
	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}
    
	//Returns the number of nodes in the tree
    public int size() {
		int num = 1;
		if (hasLeft()) {
			num += left.size();
		} 
		if (hasRight()) {
			num += right.size();
		} 
		return num;
	}
    
    public void setLeftData(T leftData){
    	this.left.data = leftData;
    	return;
    }
    
    public T getLeftData() {
    	return this.left.data;
    }
    
    public void setRightData(T rightData){
    	this.right.data = rightData;
    	return;
    }
    
    public T getRightData() {
    	return this.right.data;
    }
    
    //Creates a tokenizer to begin parsing the newickString with
    public static Tree<String> createNewickTree(String s){
    	Tree<String> t = parseNewick(new StringTokenizer(s, "(,)", true));
    	return t;
    }

    //Saves chunks of the newickString as data in trees, separated by commas and closed parentheses, ending at a semicolon
	private static Tree<String> parseNewick(StringTokenizer st) {
		String token = st.nextToken();
		if(token.equals("(")) {
			Tree<String> left = parseNewick(st);
			String comma = st.nextToken();
			Tree<String> right = parseNewick(st);
			String close = st.nextToken();
			String parent = st.nextToken();
			String[] pieces = parent.split(";");
			return new Tree<String>(pieces[0], left, right);
		}else{
			String[] pieces = token.split(";");
			return new Tree<String>(pieces[0]);
		}
	}
	
	//Creates a list to hold the upcoming list of trees
	public static ArrayList<Tree<String>> iterateTree(Tree<String> tree){
		ArrayList<Tree<String>> treeList = new ArrayList<>();
		treeList = iterateTree(treeList, tree);
		return treeList;
	}
	
	//parses through the given tree and adds every single subtree to the list
	private static ArrayList<Tree<String>> iterateTree(ArrayList<Tree<String>> list, Tree<String> tree){
		list.add(tree);
		if(tree.left != null) {
			iterateTree(list, tree.left);
		}
		if(tree.right != null) {
			iterateTree(list, tree.right);
		}
		return list;
	}
	
	//Just testing if it all works
	public static void main(String[] args) throws IOException {
		Tree<String> t = createNewickTree("((d04,d05)d02,d03)d01;");
		ArrayList<Tree<String>> treeList = new ArrayList<>();
		treeList = iterateTree(treeList, t);
		System.out.println(treeList.get(2).data);
	}
}
