package objectstests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import objects.Dialogue;
import objects.Tree;

public class DialogueTests {
	private Dialogue dialogue = new Dialogue();
	ArrayList<Tree<String>> treeList = new ArrayList<>();
	ArrayList<String> dialogueList = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
		dialogue.setDialogue("hi");
		dialogue.setID("d10");
		dialogue.setNewickTree("(d02,d03)d01;");
		dialogue.setTreeList(this.treeList);
		dialogue.setDialogueList(this.dialogueList);
	}
	
	@Test
	public void testGetDialogue() {
		assertEquals("hi", dialogue.getDialogue());
	}
	
	@Test
	public void testGetID() {
		assertEquals("d10", dialogue.getID());
	}
	
	@Test
	public void testGetNewickTree() {
		assertEquals("(d02,d03)d01;", dialogue.getNewickTree());
	}
	
	@Test
	public void testGetTreeList() {
		assertEquals(this.treeList, dialogue.getTreeList());
	}
	
	@Test
	public void testGetDialogueList() {
		assertEquals(this.dialogueList, dialogue.getDialogueList());
	}
	
	@Test
	public void testParseNewickTree() {
		dialogue.parseNewickTree(dialogue.getNewickTree());
		assertEquals("d02", dialogue.getDialogueList().get(1));
	}
}
