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
import actions.MacroCommand

class MiniEdListener (theBuffer : Buffer) extends KeyListener {
  private var buffer : Buffer = theBuffer
  private val writeCommand : WriteCommand = new WriteCommand(buffer, this)
  private val moveCommand : MoveCommand = new MoveCommand(buffer, this)
  private val selectCommand : SelectCommand = new SelectCommand(buffer, this)
  private val eraseCommand : EraseCommand = new EraseCommand(buffer)
  private val copyCommand : CopyCommand = new CopyCommand(buffer)
  private val pasteCommand : PasteCommand = new PasteCommand(buffer)
  private val cutCommand : CutCommand = new CutCommand(buffer)
  private val macroCommand : MacroCommand = new MacroCommand(this)
  private var lastChar : Char = ' '
  private var lastMove : Int = 0
  private var macroBuilding : Boolean = false
    
  def getLastChar = lastChar
  def getLastMove = lastMove
  
  def setLastChar(theChar : Char) {
    lastChar = theChar
  }
  
  def setLastMove(theMove : Int) {
    lastMove = theMove
  }
  
  def setBuffer(theBuffer : Buffer) {
    if(buffer != null)
      buffer = theBuffer
  }
  
  // Add the given command to the currently building macro
  def addCommandToMacro(command : Command) {
    macroCommand.addChild(command)
  }
  // Execute the given command
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
      if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
        if(macroBuilding)
          addCommandToMacro(eraseCommand)
        executeCommand(eraseCommand)
      }
      else // Process the key's character if there is one
        if(e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
          // Write the character if Control is not pressed
          if(!e.isControlDown()) {
            lastChar = e.getKeyChar()
            if(macroBuilding)
              addCommandToMacro(writeCommand)
            executeCommand(writeCommand)
          } else { // Execute the corresponding command if Control is pressed
            // Copy the selection with Control+C
            if(e.getKeyCode() == KeyEvent.VK_C) {
              if(macroBuilding)
            	addCommandToMacro(copyCommand)
              executeCommand(copyCommand)
            }
            // Paste the clipboard's text with Control+V
            if(e.getKeyCode() == KeyEvent.VK_V) {
              if(macroBuilding)
                addCommandToMacro(pasteCommand)
              executeCommand(pasteCommand)
            }
            // Cut the selection with Control+X
            if(e.getKeyCode() == KeyEvent.VK_X) {
              if(macroBuilding)
                addCommandToMacro(cutCommand)
              executeCommand(cutCommand)
            }
            // Start recording a macro if it's not set up
            //	End the recording if it is running
            //	Execute the macro otherwise
            if(e.getKeyCode() == KeyEvent.VK_M)
              if(!macroCommand.isSet)
                macroBuilding = !macroBuilding
              else
                if(macroBuilding)
                  macroBuilding = !macroBuilding
                else
                  executeCommand(macroCommand)
          }
        }
      e.consume()
    } else { // Process directional keys
      lastMove = e.getKeyCode()
      if(e.isShiftDown()) {
        if(macroBuilding)
          addCommandToMacro(selectCommand)
        executeCommand(selectCommand)
      }
      else {
        if(macroBuilding)
          addCommandToMacro(moveCommand)
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