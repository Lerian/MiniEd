package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BufferStateTest.class, BufferTest.class, ClipboardTest.class,
		CopyCommandTest.class, CutCommandTest.class, EraseCommandTest.class,
		GraphicalGUITest.class, MacroCommandTest.class,
		MiniEdListenerTest.class, MoveCommandTest.class,
		PasteCommandTest.class, ReplayCommandTest.class,
		SelectCommandTest.class, StatesHistoryTest.class, TextualGUITest.class,
		WriteCommandTest.class })
public class AllTests {

}
