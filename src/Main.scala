import java.util.ArrayList
import gui.GUI
import gui.GraphicalGUI
import data.Buffer

object Main {

  def main(args: Array[String]) {
    var listGUIs : ArrayList[GUI] = new ArrayList()
    var graphGUI : GraphicalGUI = new GraphicalGUI
    
    listGUIs.add(graphGUI)
    
    var buffer : Buffer = new Buffer(listGUIs)
    graphGUI.init(buffer)
  }

}