import Point.fromCSV
import scala.io.Source

object Main extends App {
 // val listofpoint = readCSV("points.cvs", fromCSV)
 val listofCountries = readCSV("data/countries.csv",Country.fromCSV)
 listofCountries.foreach(println)

//print(Country(12,"CDE","France","Europe","wiki_link","my_keyword"))

  def readCSV[T](fileName :String, 
                fonctionObject : Array[String]=> Either[String,T]
                )  : List[ Either[String,T]] = {

    val bufferedSource = Source.fromFile(fileName)
    val fileList = bufferedSource.getLines
      .toList
        .drop(1) //skip header
          .map(_.split(",").map(_.trim))
          .map(fonctionObject)
                 
  //Close the file
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
 
 /**  

  //Kugilika
  //après avoir parser et défini les vars country, airport & runways : font ref à ce qui a été défini dans les fichiers airport.scala etc
  // probleme avec readCSV 
  //non terminé
  def commandlineuser(Country, Airport, Runways) : Any = {
    println("Que voulez-vous faire ?(entrez un numéro")
    println("1. query")
    println("2. reports")
    scala.io.StdIn.readLine() match {
      case "1" => commandlinequery(Country, Airport, Runways)
      case "2" => commandlinereports(Country, Airport, Runways)
      case _ => println("error")
  }

  def commandlinequery(Country, Airport, Runways) : Any = { 
    println("Entrez un nom de pays ou code. Ex : 'Andorra' ou 'AD', 'France' ou 'FR' ")
    // à faire : fonction display_Airports_Runways - display the airports & runways at each airport
  }

  def commandlinequery(Country, Airport, Runways) : Any = { 
    println("1. 10 countries with highest number of airports (with count) and countries  with lowest number of airports.")
    println("2. Type of runways per country")
    println("3. The top 10 most common runway latitude")
  }
**/
 
}

 
