import Point.fromCSV
import scala.io.Source

object Main extends App {
  val listofpoint = readCSV("points.cvs", fromCSV)

  println(listofpoint) 

  def readCSV[T](fileName :String, fonctionObject : Array[String]=> Option[T]) : List[Option[T]] = {
    val bufferedSource = Source.fromFile("points.csv")
    val fileList = bufferedSource.getLines
      .toList
          .map(_.split(",").map(_.trim))           
          .map(fonctionObject)
  
  //Close the file
    bufferedSource.close

    return fileList
  }

}

 