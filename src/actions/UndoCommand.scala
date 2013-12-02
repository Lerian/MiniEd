package actions

import data.Buffer

class UndoCommand(theBuffer : Buffer) extends Command {
  override def execute() {
    theBuffer.undo();
  }
}