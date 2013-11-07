package data

class Clipboard {
	private var text : String = ""
	  
	def getText = text
	def setText(newText : String) {
	  text = newText
	}
}