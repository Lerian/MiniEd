package gui

import java.awt.event.KeyListener
import java.awt.event.KeyEvent
import data.Buffer
import actions.Command
import actions.WriteCommand
import actions.MoveCommand
import actions.SelectCommand

class MiniEdListener (theBuffer : Buffer) extends KeyListener {
  private var buffer : Buffer = theBuffer
  private val writeCommand : WriteCommand = new WriteCommand(buffer, this);
  private val moveCommand : MoveCommand = new MoveCommand(buffer, this);
  private val selectCommand : SelectCommand = new SelectCommand(buffer, this);
  private var lastChar : Char = ' '
  private var lastMove : Int = 0
    
  def getLastChar = lastChar
  def getLastMove = lastMove
  
  def setBuffer(theBuffer : Buffer) {
    if(buffer != null)
      buffer = theBuffer
  }
  
  def executeCommand(command : Command) {
    command.execute()
  }
  
  override def keyPressed(e : KeyEvent) {
    if(!isDirectionalKey(e)) {
      e.consume()
    } else {
      lastMove = e.getKeyCode()
      if(e.isShiftDown())
        executeCommand(selectCommand)
      else
        executeCommand(moveCommand)
    }
  }
  
  override def keyReleased(e : KeyEvent) {
    if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
      System.exit(0)
    
    e.consume()
  }
  
  override def keyTyped(e : KeyEvent) {
    if(!e.isControlDown()) {
      lastChar = e.getKeyChar()
      executeCommand(writeCommand)
    }
    e.consume()
  }
  
  // Tell if the key is one of the four non-numpad directional keys
  def isDirectionalKey(e : KeyEvent)
  	= (((e.getKeyCode() == KeyEvent.VK_RIGHT)
  	|| (e.getKeyCode() == KeyEvent.VK_LEFT))
  	||((e.getKeyCode() == KeyEvent.VK_UP)
  	||(e.getKeyCode() == KeyEvent.VK_DOWN)))
}