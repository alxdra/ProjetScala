import Point.fromCSV
import scala.io.Source

object Main extends App {
  val listofpoint = readCSV("points.cvs", fromCSV)
 
  println(listofpoint) 

  def readCSV[T](fileName :String, fonctionObject : Array[Either[NumberFormatException,Float]]=> Either[String,T]) : List[ Either[String,T]] = {
    val bufferedSource = Source.fromFile("points.csv")

    val fileList = bufferedSource.getLines
      .toList
          .map(_.split(",").map(_.trim).map(valuesEither))
          .map(fonctionObject)
                 
  //Close the file
    bufferedSource.close

    return fileList
  }

   def valuesEither(value: String): Either[NumberFormatException, Float] = {
    try {
     Right(value.toFloat)
    } catch {
    case ex: NumberFormatException => Left(ex)
    }
  }

}

 