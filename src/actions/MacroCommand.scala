package actions

import java.util.ArrayList
import gui.MiniEdListener
import data.Buffer

class MacroCommand(theManager : MiniEdListener) extends Command {
  private val manager = theManager
  private var children : ArrayList[Command] = new ArrayList()
  private var writtenChars : ArrayList[Char] = new ArrayList()
  private var doneMoves : ArrayList[Int] = new ArrayList()
  
  override def execute() {
    var index : Int = 0
    for(x <- index to children.size()-1) {
      manager.setLastChar(writtenChars.get(x))
      manager.setLastMove(doneMoves.get(x))
      children.get(x).execute()
    }
  }
  
  def addChild(newChild : Command) {
    children.add(newChild)
    writtenChars.add(manager.getLastChar)
    doneMoves.add(manager.getLastMove)
  }
  
  def isSet = (children.size() != 0)
}