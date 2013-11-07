import gui.GUI
import java.util.ArrayList
import gui.TextualGUI
import data.Buffer

object Main {

  def main(args: Array[String]) {
    val listGUIs : ArrayList[GUI] = new ArrayList[GUI]
    val textGUI : TextualGUI = new TextualGUI
    
    listGUIs.add(textGUI)
    
    val buffer : Buffer = new Buffer(listGUIs)
  }

}