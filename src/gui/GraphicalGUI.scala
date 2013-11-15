package gui

import javax.swing.JFrame
import javax.swing.JTextArea
import javax.swing.JScrollPane
import java.awt.Dimension
import data.Buffer

class GraphicalGUI extends GUI {
  private var frame : JFrame = new JFrame("MiniEd")
  private var textArea : JTextArea = new JTextArea("")
  
  override def init(newbuffer : Buffer) {
    // initialisation du buffer
    setBuffer(newbuffer)
    getBuffer.addGui(this)
    
    // initialisation de la fenêtre
    textArea.setText(getBuffer.getText)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) // la fenêtre doit se fermer quand on clique sur la croix rouge
    frame.getContentPane().add(textArea) // on ajoute la zone de texte dans la fenêtre
    frame.getContentPane().add(new JScrollPane(textArea)) // on ajoute des scrollbar à la fenêtre si nécessaire
    frame.setMinimumSize(new Dimension(200, 200)) // on demande d'attribuer une taille minimale à la fenêtre
    frame.setLocationRelativeTo(null) // on centre la fenêtre
    frame.setVisible(true) // on rend la fenêtre visible
    
    // initialisation du keyListener
    textArea.addKeyListener(getBuffer.getCommandManager)
  }
  
  override def refreshTextDisplay {
    textArea.setText(getBuffer.getText)
    frame.repaint()
  }

  override def refreshCaretDisplay {
    textArea.setCaretPosition(getBuffer.getCursorPosition)
  }
}