package actions

import data.Buffer

class PasteCommand(theBuffer : Buffer) extends Command {
  override def execute() {
    theBuffer.paste()
  }
}