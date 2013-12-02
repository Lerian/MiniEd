package data

import java.util.ArrayList

class StatesHistory {
	private var savedStates : ArrayList[BufferState] = new ArrayList
	private var currentState : Int = -1
	
	def addState(state : BufferState) {
	  if(savedStates.size() != currentState+1) {
	    var tmpArray : ArrayList[BufferState] = new ArrayList
	    for(x <- 0 to currentState)
	      tmpArray.add(savedStates.get(x))
	    savedStates = tmpArray
	  }
	  savedStates.add(state)
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
	
	def getNextState() : BufferState = {
	  if(currentState < savedStates.size()-1) {
	    currentState = currentState +1
	    return savedStates.get(currentState)
	  }
	  else
	    return null
	}
}