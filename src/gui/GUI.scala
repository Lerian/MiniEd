package gui

import data.Buffer

trait GUI {
	var buffer : Buffer = null
	
	def getBuffer = buffer
	def setBuffer(newBuffer : Buffer) {
	  buffer = newBuffer
	}
	
	def init(newBuffer : Buffer)
	def refreshTextDisplay
	def refreshCaretDisplay
}