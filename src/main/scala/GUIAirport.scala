import scala.io.Source
import javax.management.monitor.CounterMonitor
import scala.swing._

class UI extends MainFrame {
  title = "GUI Airport"
  contents = new TextArea
  menuBar = new MenuBar {
    contents += new Menu("Query")
    contents += new Menu("Reports"){
      contents += new MenuItem(Action("Top10 Country"){
        //Main.displayAirports(scala.io.StdIn.readLine())
        println("test")
      })
      contents += new MenuItem("Type runways"){}
      contents += new MenuItem("Top10 Runways"){}
      contents += new MenuItem(Action("Exit"){
      sys.exit(0)
    })
    }
    

  }
  size = new Dimension(500,500)
  centerOnScreen()
}

object GUIAirport {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }

  
}
