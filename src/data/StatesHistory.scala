package data

import java.util.ArrayList

class StatesHistory {
	private var savedStates : ArrayList[BufferState] = new ArrayList
	private var currentState : Int = -1
	
	def addState(state : BufferState) {
	  if(savedStates.size() == currentState+1)
		savedStates.add(state)
	  else
	    savedStates.set(currentState+1, state)
	  currentState = currentState +1
	}
	
	def getHistorySize() = savedStates.size()
	def getCurrentHistorySize() = currentState+1
	def getState(index : Int) = savedStates.get(index)
	
	def getLastState() : BufferState = {
	  if(currentState >= 1) {
	    currentState = currentState - 1
	    return savedStates.get(currentState)
	  }
	  else
	    return null
	}
}