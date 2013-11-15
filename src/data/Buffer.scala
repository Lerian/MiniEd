package data

import java.util.List
import java.util.ArrayList
import java.awt.event.KeyEvent
import gui.GUI
import gui.MiniEdListener

class Buffer(listGUIs : List[GUI]) {
	private var text : String = ""
	private var cursorPosition : Int = 0
	private var selectionBeginning : Int = cursorPosition
	private var selectionEnd : Int = cursorPosition
	private var clipboard : Clipboard = new Clipboard
	private var GUIs : List[GUI] = listGUIs
	private var commandManager : MiniEdListener = new MiniEdListener(this)
	
	// Add a GUI
	def addGui(gui : GUI) {
	  GUIs.add(gui)
	}
	
	// Refresh every GUI
	def refreshGuis() {
	  for (x <- 0 to GUIs.size()-1) {
	    GUIs.get(x).refreshTextDisplay
	    GUIs.get(x).refreshCaretDisplay
	  }
	}
	
	// Refresh every GUI's caret
	def refreshCarets() {
	  for (x <- 0 to GUIs.size()-1)
	    GUIs.get(x).refreshCaretDisplay
	}	
	
	def getText = text
	def getCommandManager = commandManager
	def getCursorPosition = cursorPosition
	
	def setText(newText : String) {
	  text = newText
	}
	def setCursorPosition(newPos : Int) {
	  cursorPosition = newPos
	}
	
	// Move the cursor in the given direction
	def move(move : Int) {
	  if (selectionBeginning != selectionEnd) {
	    move match {//TODO les flèches haut/bas
		    case KeyEvent.VK_RIGHT => //if(selectionEnd+1 < text.size)
		    							cursorPosition = selectionEnd//+1
		    case KeyEvent.VK_LEFT => //if(selectionBeginning-1 >= 0)
		    							cursorPosition = selectionBeginning//-1
		    case KeyEvent.VK_UP =>{}
		    case KeyEvent.VK_DOWN => {}
		  }
		  selectionBeginning = cursorPosition
	      selectionEnd = cursorPosition
	  } else {//TODO faire que ça marche
		  move match {//TODO les flèches haut/bas
		    case KeyEvent.VK_RIGHT => if(cursorPosition < text.size)
		    							//setCursorPosition(cursorPosition + 1)
		    							cursorPosition = cursorPosition+1
		    case KeyEvent.VK_LEFT => if(cursorPosition > 0)
		    							//setCursorPosition(cursorPosition - 1)
		    							cursorPosition = cursorPosition-1
		    case KeyEvent.VK_UP =>{}
		    case KeyEvent.VK_DOWN => {}
		  }
		  selectionBeginning = cursorPosition
	      selectionEnd = cursorPosition
	      System.err.println(cursorPosition)
	  }
	  refreshCarets()
	}
	
	// Erase every character in the selection (the one before the cursor if there is no selection)
	def erase() {
	  var beginning : String = ""
	  var ending : String = ""
	  // Work on the text
	  if (selectionBeginning != selectionEnd) {
	    if (selectionBeginning != 0)
	    	beginning = text.substring(0, selectionBeginning)
	    if (selectionEnd != text.size)
	    	ending = text.substring(selectionEnd, text.length())
	    // Update the cursor's position and the selection
	    cursorPosition = selectionBeginning
	    selectionBeginning = cursorPosition
	    selectionEnd = cursorPosition
	  } else {
	    if (cursorPosition != 0)
	    	beginning = text.substring(0, cursorPosition-1)
	    if (cursorPosition != text.size)
			ending = text.substring(cursorPosition, text.length())
	    // Update the cursor's position and the selection
	    if(cursorPosition-1 >= 0)
	      cursorPosition = cursorPosition -1
	    selectionBeginning = cursorPosition
	    selectionEnd = cursorPosition
	  }
	  text = beginning.concat(ending)
	  refreshGuis()
	}
	
	// Copy the clipboard's content at the selection's position (at the cursor's if there is no selection)
	def paste() {
	  // Work on the text
	  if (selectionBeginning != selectionEnd) {
	    var beginning : String = text.substring(0, selectionBeginning)
		var ending : String = text.substring(selectionEnd, text.length())
		text = beginning.concat(clipboard.getText).concat(ending)
	  } else {
		var beginning : String = text.substring(0, cursorPosition)
		var ending : String = text.substring(cursorPosition, text.length())
		text = beginning.concat(clipboard.getText).concat(ending)
	  }
	  // Update the cursor's position and the selection
	  cursorPosition = selectionBeginning + clipboard.getText.length()
	  selectionBeginning = cursorPosition
	  selectionEnd = cursorPosition
	  refreshGuis()
	}
	
	// If there is a selection, copy it's content to the clipboard, then erase it
	def cut() {
	  if (selectionBeginning != selectionEnd) {
		copy()
		erase()
	  }
	}
	
	//
	def select(move : Int) {
	  if (selectionBeginning != selectionEnd) {
		  move match {//TODO les flèches haut/bas
		    case KeyEvent.VK_RIGHT =>
		      if(cursorPosition < text.size) {
		        cursorPosition = cursorPosition + 1
		        if(cursorPosition <= selectionEnd) {
		          selectionBeginning = cursorPosition
		        }
		        if(cursorPosition > selectionEnd) {
		          selectionEnd = cursorPosition
		        }
		      }
		    case KeyEvent.VK_LEFT =>
		      if(cursorPosition > 0) {
		        cursorPosition = cursorPosition - 1
		        if(cursorPosition < selectionBeginning) {
		          selectionBeginning = cursorPosition
		        }
		        if(cursorPosition >= selectionBeginning) {
		          selectionEnd = cursorPosition
		        }
		      }
		    case KeyEvent.VK_UP =>{}
		    case KeyEvent.VK_DOWN => {}
		  }
	  } else {
		  move match {//TODO les flèches haut/bas
		    case KeyEvent.VK_RIGHT =>
		      if(cursorPosition < text.size) {
		        cursorPosition = cursorPosition + 1
		        selectionEnd = cursorPosition
		      }
		    case KeyEvent.VK_LEFT =>
		      if(cursorPosition > 0) {
		        cursorPosition = cursorPosition - 1
		        selectionBeginning = cursorPosition
		      }
		    case KeyEvent.VK_UP =>{}
		    case KeyEvent.VK_DOWN => {}
		  }
	  }
	}
	
	// If there is a selection, copy it's content to the clipboard
	def copy() {
	  if (selectionBeginning != selectionEnd) {
		var newClipText : String = text.substring(selectionBeginning, selectionEnd)
		clipboard.setText(newClipText)
	  }
	}
	
	// Write the string at the selection's position (at the cursor's if there is no selection) 
	def write(char : Char) {
	  var beginning : String = ""
	  var ending : String = ""
	  // Work on the text
	  if (selectionBeginning != selectionEnd) {
	    if (selectionBeginning != 0)
	    	beginning = text.substring(0, selectionBeginning)
	    if (selectionEnd != text.size)
	    	ending = text.substring(selectionEnd, text.length())
	  } else {
	    if (cursorPosition != 0)
	    	beginning = text.substring(0, cursorPosition)
	    if (cursorPosition != text.size)
			ending = text.substring(cursorPosition, text.length())
	  }
	  text = beginning.concat(char.toString).concat(ending)
	  
	  // Update the cursor's position and the selection
	  cursorPosition = selectionBeginning + 1
	  selectionBeginning = cursorPosition
	  selectionEnd = cursorPosition
	  
	  refreshGuis()
	}
}