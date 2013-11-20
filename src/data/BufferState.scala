package data

class BufferState(theText : String, theCursorPosition : Int, theSelectionBeginning : Int, theSelectionEnd : Int) {
	private var text : String = theText
	private var cursorPosition : Int = theCursorPosition
	private var selectionBeginning : Int = theSelectionBeginning
	private var selectionEnd : Int = theSelectionEnd
	
	def getSavedText = text
	def getSavedCursorPositon = cursorPosition
	def getSavedSelectionBeginning = selectionBeginning
	def getSavedSelectionEnd = selectionEnd
}