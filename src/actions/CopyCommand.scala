package actions

import data.Buffer

class CopyCommand(theBuffer : Buffer) extends Command {
  override def execute() {
    theBuffer.copy()
  }
}