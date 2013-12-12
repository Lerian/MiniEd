package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BufferStateTest.class, BufferTest.class, ClipboardTest.class,
		GraphicalGUITest.class, MacroCommandTest.class,
		MiniEdListenerTest.class, ReplayCommandTest.class, StatesHistoryTest.class, TextualGUITest.class })
public class AllTests {

}
