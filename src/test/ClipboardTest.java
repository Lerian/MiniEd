package test;

import static org.junit.Assert.*;
import data.Clipboard;

import org.junit.Test;

public class ClipboardTest {

	@Test
	public void testClipboard() {
		Clipboard clipboard = null;
		clipboard = new Clipboard();
		assertNotNull(clipboard);
	}
	
	@Test
	public void testGetText() {
		Clipboard clipboard = new Clipboard();
		assertEquals("",clipboard.getText());
	}

	@Test
	public void testSetText() {
		Clipboard clipboard = new Clipboard();
		clipboard.setText("toto");
		assertEquals("toto",clipboard.getText());
	}

}
