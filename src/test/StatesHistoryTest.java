package test;

import static org.junit.Assert.*;

import org.junit.Test;

import data.BufferState;
import data.StatesHistory;

public class StatesHistoryTest {
	@Test
	public void testStatesHistory() {
		StatesHistory history = null;
		history = new StatesHistory();
		assertNotNull(history);
	}

	@Test
	public void testAddState() {
		StatesHistory history = new StatesHistory();
		assertEquals(0,history.getHistorySize());
		BufferState state = new BufferState("test",0,1,2);
		history.addState(state);
		assertEquals(1,history.getHistorySize());
	}

	@Test
	public void testGetHistorySize() {
		StatesHistory history = new StatesHistory();
		assertEquals(0,history.getHistorySize());
		history.addState(new BufferState("test",0,1,2));
		assertEquals(1,history.getHistorySize());
	}

	@Test
	public void testGetState() {
		StatesHistory history = new StatesHistory();
		BufferState state = new BufferState("test",0,1,2);
		history.addState(state);
		assertEquals(history.getState(0),state);
	}

}
