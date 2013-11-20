package test;

import static org.junit.Assert.*;

import org.junit.Test;
import data.BufferState;

public class BufferStateTest {

	@Test
	public void testBufferState() {
		BufferState bufferState = null;
		bufferState = new BufferState("",0,1,2);
		assertNotNull(bufferState);
	}
	
	@Test
	public void testGetSavedText() {
		BufferState bufferState = new BufferState("texte",0,1,2);
		assertEquals("texte",bufferState.getSavedText());
	}

	@Test
	public void testGetSavedCursorPositon() {
		BufferState bufferState = new BufferState("texte",0,1,2);
		assertEquals(0,bufferState.getSavedCursorPositon());
	}

	@Test
	public void testGetSavedSelectionBeginning() {
		BufferState bufferState = new BufferState("texte",0,1,2);
		assertEquals(1,bufferState.getSavedSelectionBeginning());
	}

	@Test
	public void testGetSavedSelectionEnd() {
		BufferState bufferState = new BufferState("texte",0,1,2);
		assertEquals(2,bufferState.getSavedSelectionEnd());
	}

}
