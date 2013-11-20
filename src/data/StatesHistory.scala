package data

import java.util.ArrayList

class StatesHistory {
	private var savedStates : ArrayList[BufferState] = new ArrayList
	
	def addState(state : BufferState) {
	  savedStates.add(state)
	}
	
	def getHistorySize() = savedStates.size()
	def getState(index : Int) = savedStates.get(index)
}