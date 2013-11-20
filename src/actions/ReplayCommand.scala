package actions

import data.Buffer

class ReplayCommand(theBuffer : Buffer) extends Command {
	override def execute() {
	  theBuffer.replay()
	}
}