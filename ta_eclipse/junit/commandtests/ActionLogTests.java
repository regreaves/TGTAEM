package commandtests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import command.Action;
import command.ActionLog;

public class ActionLogTests {
	private ActionLog actionLog = new ActionLog();
	ArrayList<Action> history = new ArrayList<>();
	Action action = new Action("action", null, null, null);
	Action action2 = new Action("action2", null, null, null);
	
	@Before
	public void setUp() throws Exception {
		history.add(action);
		actionLog.setHistory(history);
		actionLog.addAction(action2);
	}

	@Test
	public void testLastAction() {
		assertEquals(action2.getName(), actionLog.lastAction().getName());
	}

	@Test
	public void testPrevAction() {
		assertTrue(action2.getName(), actionLog.prevAction(action.getName()));
	}

}
