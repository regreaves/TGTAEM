package objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Tree class is partially based on code found at https://www.cs.dartmouth.edu/~cbk/classes/10/15spring/notes/7/code/BinaryTree.java
//@author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
public class Tree<T> {
	private Tree<T> left, right;
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
    
    public Tree<T> getLeft(){
    	return this.left;
    }
    
    public Tree<T> getRight(){
    	return this.right;
    }
    
    public static Tree<String> createNewickTree(String s){
    	Tree<String> t = parseNewick(new StringTokenizer(s, "(,)", true));
    	return t;
    }

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
	
	public static ArrayList<Tree<String>> iterateTree(Tree<String> tree){
		ArrayList<Tree<String>> treeList = new ArrayList<>();
		treeList = iterateTree(treeList, tree);
		return treeList;
	}
	
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
	
	public static void main(String[] args) throws IOException {
		Tree<String> t = createNewickTree("((d04,d05)d02,d03)d01;");
		ArrayList<Tree<String>> treeList = new ArrayList<>();
		treeList = iterateTree(treeList, t);
		System.out.println(treeList.get(2).data);
	}
}
