package actions

import data.Buffer
import gui.MiniEdListener

class MoveCommand(theBuffer : Buffer, theManager : MiniEdListener) extends Command {
  override def execute() {
    theBuffer.move(theManager.getLastMove)
  }
}