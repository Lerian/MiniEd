package actions

import data.Buffer

class EraseCommand(theBuffer : Buffer) extends Command {
  override def execute() {
    theBuffer.erase()
  }
}