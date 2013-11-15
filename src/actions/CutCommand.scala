package actions

import data.Buffer

class CutCommand(theBuffer : Buffer) extends Command {
  override def execute() {
    theBuffer.cut()
  }
}