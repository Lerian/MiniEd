package actions

import data.Buffer

class RedoCommand(theBuffer : Buffer) extends Command {
  def execute() {
    theBuffer.redo()
  }
}