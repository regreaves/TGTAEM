package objectstests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import objects.Tree;

public class TreeTests {
	Tree<String> tree = new Tree<String>(this.data);
	String data = "hi";
	Tree<String> left = new Tree<String>(this.leftdata);
	String leftdata = "hello";
	Tree<String> right = new Tree<String>(this.rightdata);
	String rightdata = "wassup";
	
	@Before
	public void setUp() throws Exception {
		tree.setLeft(this.left);
		tree.setLeftData(this.leftdata);
		tree.setRight(this.right);
		tree.setRightData(this.rightdata);
	}
	
	@Test
	public void testGetLeft() {
		assertEquals(this.left, tree.getLeft());
	}
	
	@Test
	public void testGetRight() {
		assertEquals(this.right, tree.getRight());
	}
	
	@Test
	public void testHasLeft() {
		assertTrue(tree.hasLeft());
	}
	
	@Test
	public void testHasRight() {
		assertTrue(tree.hasRight());
	}
	
	@Test
	public void testSize() {
		assertEquals(3, tree.size());
	}
	
	@Test
	public void testGetLeftData() {
		assertEquals(this.leftdata, tree.getLeftData());
	}
	
	@Test
	public void testGetRightData() {
		assertEquals(this.rightdata, tree.getRightData());
	}

}
