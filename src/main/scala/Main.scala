import scala.io.Source
import javax.management.monitor.CounterMonitor

object Main extends App {

  val a = Airport(12,"CDG","big","Don't Crash",None,None,None,None,"S",None, None, None, None, None, None, None, None,None)
  val b = Airport(12,"CDG","big","Don't Crash",None,None,None,None,"O",None, None, None, None, None, None, None, None,None)
  val l = List(a,b)
  
  val c = Country(1,
                  "S",
                  "Lalaland",
                  "NeverLand",
                   Nil,
                   None)

  
  c.airports = Some(c.addAirports(l))
  println(c.airports)

 /* val allCountries = readCSV("data/countries.csv",Country.fromCSV).colle
 ct{
        case Right(x)=>x 
        case Left(x)=>None
      }
  
  allCountries.foreach(println)

  val allAirports = readCSV("data/airports.csv", Airport.fromCSV).collect{
        case Right(x)=>x 
        case Left(x)=>None
      }
  //listofAirports.foreach(println)*/

 // allCountries(1).addAirports(allAirports, Nil)

 //val listofRunways = readCSV("data/runways.csv", Runway.fromCSV)
 //listofRunways.foreach(println)
//print(Country(12,"CDE","France","Europe","wiki_link","my_keyword"))

  def readCSV[T](fileName :String, 
                fonctionObject : Array[String]=> Either[String,T], 
                )  : List[Either[String,T]]= {

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
}

 