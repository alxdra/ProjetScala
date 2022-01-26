import Point.fromCSV
import scala.io.Source

object Main extends App {

  val listofCountries = readCSV("data/countries.csv",Country.fromCSV)
  listofCountries.foreach(println)

 //val listofAirports = readCSV("data/airports.csv", Airport.fromCSV)
 //listofAirports.foreach(println)
//print(Country(12,"CDE","France","Europe","wiki_link","my_keyword"))

  def readCSV[T](fileName :String, 
                fonctionObject : Array[String]=> Either[String,T]
                )  : List[ Either[String,T]] = {

    val bufferedSource = Source.fromFile(fileName)
    val fileList = bufferedSource.getLines
      .toList
      .drop(1) //skip header
      .map(_.split(",")
      .map(_.trim))
      .map(_.map(_.replaceAll("^\"|\"$", "")))
      .map(fonctionObject)
                 
  //Close the files
    bufferedSource.close
    return fileList
  }

 /* def readCSV[T](fileName :String, 
                fonctionObject : Array[Either[NumberFormatException,Float]]=> Either[String,T]
                )  : List[ Either[String,T]] = {
    val bufferedSource = Source.fromFile("points.csv")

    val fileList = bufferedSource.getLines
      .toList
          .map(_.split(",").map(_.trim).map(valuesEither))
          .map(fonctionObject)
                 
  //Close the file
    bufferedSource.close
    return fileList
  }
*/
   def valuesEither(value: String): Either[NumberFormatException, Float] = {
    try {
     Right(value.toFloat)
    } catch {
    case ex: NumberFormatException => Left(ex)
    }
  }
}

 