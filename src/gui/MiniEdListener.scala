package gui

import java.awt.event.KeyListener
import java.awt.event.KeyEvent
import data.Buffer
import actions.Command
import actions.WriteCommand
import actions.MoveCommand
import actions.SelectCommand
import actions.EraseCommand
import actions.CopyCommand
import actions.PasteCommand
import actions.CutCommand

class MiniEdListener (theBuffer : Buffer) extends KeyListener {
  private var buffer : Buffer = theBuffer
  private val writeCommand : WriteCommand = new WriteCommand(buffer, this)
  private val moveCommand : MoveCommand = new MoveCommand(buffer, this)
  private val selectCommand : SelectCommand = new SelectCommand(buffer, this)
  private val eraseCommand : EraseCommand = new EraseCommand(buffer)
  private val copyCommand : CopyCommand = new CopyCommand(buffer)
  private val pasteCommand : PasteCommand = new PasteCommand(buffer)
  private val cutCommand : CutCommand = new CutCommand(buffer)
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
    // Close the application with Escape
    if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
      System.exit(0) //TODO remplacer par un appel à une commande de close
    // Process non directional keys
    if(!isDirectionalKey(e)) {
      // Erase content with Backspace
      if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        executeCommand(eraseCommand)
      else // Process the key's character if there is one
        if(e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
          // Write the character if Control is not pressed
          if(!e.isControlDown()) {
            lastChar = e.getKeyChar()
            executeCommand(writeCommand)
          } else { // Execute the corresponding command if Control is pressed
            // Copy the selection with Control+C
            if(e.getKeyCode() == KeyEvent.VK_C)
              executeCommand(copyCommand)
            // Paste the clipboard's text with Control+V
            if(e.getKeyCode() == KeyEvent.VK_V)
              executeCommand(pasteCommand)
            // Cut the selection with Control+X
            if(e.getKeyCode() == KeyEvent.VK_X)
              executeCommand(cutCommand)
          }
        }
      e.consume()
    } else { // Process directional keys
      lastMove = e.getKeyCode()
      if(e.isShiftDown())
        executeCommand(selectCommand)
      else {
        executeCommand(moveCommand)
        e.consume()
      }
    }
  }
  
  override def keyReleased(e : KeyEvent) {    
    e.consume()
  }
  
  override def keyTyped(e : KeyEvent) {
    e.consume()
  }
  
  // Tell if the key is one of the four non-numpad directional keys
  def isDirectionalKey(e : KeyEvent)
  	= (((e.getKeyCode() == KeyEvent.VK_RIGHT)
  	|| (e.getKeyCode() == KeyEvent.VK_LEFT))
  	||((e.getKeyCode() == KeyEvent.VK_UP)
  	||(e.getKeyCode() == KeyEvent.VK_DOWN)))
}