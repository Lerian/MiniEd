package data

import gui.GUI
import java.util.ArrayList

class Buffer(listGUIs : ArrayList[GUI]) {
	private var text : String = ""
	private var cursorPosition : Integer = 0
	private var selectionBeginning : Integer = cursorPosition
	private var selectionEnd : Integer = cursorPosition
	private var clipboard : Clipboard = new Clipboard()
	private var GUIs : ArrayList[GUI] = listGUIs
	
	//
	def move() {}
	
	// Erase every character in the selection (the one before the cursor if there is no selection)
	def erase() {
	  if (selectionBeginning != selectionEnd) {
	    // Work on the text
	    val beginning : String = text.substring(0, selectionBeginning+1)
		val ending : String = text.substring(selectionEnd+1, text.length())
		text = beginning.concat(ending)
		// Update the cursor's position and the selection
		cursorPosition = selectionBeginning
		selectionBeginning = cursorPosition
		selectionEnd = cursorPosition
	  } else {
	    // Work on the text
		val beginning : String = text.substring(0, cursorPosition)
		val ending : String = text.substring(cursorPosition+1, text.length())
		text = beginning.concat(ending)
		// Update the cursor's position and the selection
		cursorPosition - 1
	  }
	}
	
	// Copy the clipboard's content at the selection's position (at the cursor's if there is no selection)
	def paste() {
	  // Work on the text
	  if (selectionBeginning != selectionEnd) {
	    val beginning : String = text.substring(0, selectionBeginning+1)
		val ending : String = text.substring(selectionEnd+1, text.length())
		text = beginning.concat(clipboard.getText).concat(ending)
	  } else {
		val beginning : String = text.substring(0, cursorPosition+1)
		val ending : String = text.substring(cursorPosition+1, text.length())
		text = beginning.concat(clipboard.getText).concat(ending)
	  }
	  // Update the cursor's position and the selection
	  cursorPosition = selectionBeginning + clipboard.getText.length()
	  selectionBeginning = cursorPosition
	  selectionEnd = cursorPosition
	}
	
	// If there is a selection, copy it's content to the clipboard, then erase it
	def cut() {
	  if (selectionBeginning != selectionEnd) {
		copy()
		erase()
	  }
	}
	
	//
	def select() {}
	
	// If there is a selection, copy it's content to the clipboard
	def copy() {
	  if (selectionBeginning != selectionEnd) {
		val newClipText : String = text.substring(selectionBeginning, selectionEnd+1)
		clipboard.setText(newClipText)
	  }
	}
	
	//
	def write() {}
}