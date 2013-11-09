package actions

import data.Buffer
import gui.MiniEdListener

class WriteCommand(theBuffer : Buffer, theManager : MiniEdListener) extends Command {
  override def execute() {
    theBuffer.write(theManager.getLastChar)
  }
}