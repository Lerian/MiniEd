package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.Buffer;

public class BufferTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuffer() {
		Buffer buffer = null;
		buffer = new Buffer(null);
		assertNotNull(buffer);
	}

	@Test
	public void testGetText() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetText() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCursorPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCursorPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommandManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddGui() {
		fail("Not yet implemented");
	}

	@Test
	public void testRefreshGuis() {
		fail("Not yet implemented");
	}

	@Test
	public void testRefreshCarets() {
		fail("Not yet implemented");
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testErase() {
		fail("Not yet implemented");
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
