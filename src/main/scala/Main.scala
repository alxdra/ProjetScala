import Point.fromCSV
import scala.io.Source
object Main extends App {
  val listofpoint = readCSV("points.cvs")

  println(listofpoint) 

  def readCSV(fileName :String) : List[Option[Point]] = {
    val bufferedSource = Source.fromFile("points.csv")
    val fileList = bufferedSource.getLines
      .toList
          .map(_.split(",").map(_.trim))           
          .map(fromCSV)  
  
  //Close the file
    bufferedSource.close

    return fileList
  }

}

 