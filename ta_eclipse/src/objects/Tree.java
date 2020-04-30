package objects;
//WORK IN PROGRESS
public class Tree<T> {
	private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.left = null;
        root.right = null;
    }

    public static class Node<T> {
        T data;
        Node<T> parent;
        Node<T> left;
        Node<T> right;
    }
    
    public Node<T> addLeftNode(T left) {
        Node<T> leftNode = new Node<T>();
        leftNode.parent = this.root;
        this.root.left.data = leftNode.data;
        return leftNode;
    }
    
    public Node<T> addRightNode(T right) {
        Node<T> rightNode = new Node<T>();
        rightNode.parent = this.root;
        this.root.right.data = rightNode.data;
        return rightNode;
    }
}
