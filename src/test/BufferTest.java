package test;

import static org.junit.Assert.*;

import org.junit.Test;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import data.Buffer;
import gui.GUI;
import gui.GraphicalGUI;
import gui.MiniEdListener;

public class BufferTest {

	@Test
	public void testBuffer() {
		Buffer buffer = null;
		buffer = new Buffer(null);
		assertNotNull(buffer);
	}

	@Test
	public void testGetText() {
		Buffer buffer = new Buffer(null);
		assertEquals("",buffer.getText());
	}

	@Test
	public void testSetText() {
		Buffer buffer = new Buffer(null);
		buffer.setText("char string");
		assertEquals("char string",buffer.getText());
	}

	@Test
	public void testGetCursorPosition() {
		Buffer buffer = new Buffer(null);
		assertEquals(0,buffer.getCursorPosition());
	}

	@Test
	public void testSetCursorPosition() {
		Buffer buffer = new Buffer(null);
		buffer.setCursorPosition(1);
		assertEquals(1,buffer.getCursorPosition());
	}

	@Test
	public void testGetCommandManager() {
		Buffer buffer = new Buffer(null);
		assertNotNull(buffer.getCommandManager());
		assertTrue(buffer.getCommandManager() instanceof MiniEdListener);
	}

	@Test
	public void testMove() { //TODO le test avec sélection
	    ArrayList<GUI> listGUIs = new ArrayList<GUI>();
	    GraphicalGUI graphGUI = new GraphicalGUI();
	    listGUIs.add(graphGUI);
		Buffer buffer = new Buffer(listGUIs);
	    graphGUI.init(buffer);
		// test mouvement à gauche si curseur à 0
		buffer.move(KeyEvent.VK_LEFT);
		assertEquals(0,buffer.getCursorPosition());
		// test mouvement à droite si curseur au bout du texte
		buffer.move(KeyEvent.VK_RIGHT);
		assertEquals(0,buffer.getCursorPosition());
		// test mouvement à gauche si possible
		buffer.write('a');
		buffer.move(KeyEvent.VK_LEFT);
		assertEquals(0,buffer.getCursorPosition());
		// test mouvement à droite si possible
		buffer.move(KeyEvent.VK_RIGHT);
		assertEquals(1,buffer.getCursorPosition());
		
	}

	@Test
	public void testErase() { // TODO le test avec sélection
		ArrayList<GUI> listGUIs = new ArrayList<GUI>();
	    GraphicalGUI graphGUI = new GraphicalGUI();
	    listGUIs.add(graphGUI);
		Buffer buffer = new Buffer(listGUIs);
	    graphGUI.init(buffer);
	    buffer.write('a');
	    assertEquals(1,buffer.getCursorPosition());
	    buffer.erase();
	    assertEquals(0,buffer.getCursorPosition());
	}

	@Test
	public void testPaste() {
		fail("Not yet implemented");
	}

	@Test
	public void testCut() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelect() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	public void testWrite() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveState() {
		fail("Not yet implemented");
	}

	@Test
	public void testRestoreFromState() {
		fail("Not yet implemented");
	}

	@Test
	public void testReplay() {
		fail("Not yet implemented");
	}

}
