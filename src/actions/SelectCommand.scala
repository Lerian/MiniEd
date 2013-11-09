package actions

import data.Buffer
import gui.MiniEdListener

class SelectCommand(theBuffer : Buffer, theManager : MiniEdListener) extends Command {
  override def execute() {
    theBuffer.select(theManager.getLastMove)
  }
}