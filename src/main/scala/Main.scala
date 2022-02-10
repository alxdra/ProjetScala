import scala.io.Source
import javax.management.monitor.CounterMonitor

object Main extends App {
  val allCountries = readCSV("data/countries.csv",Country.fromCSV).collect{
        case Right(x)=>x
      }
  
  //allCountries.foreach(println)

  val allAirports = readCSV("data/airports.csv", Airport.fromCSV).collect{
        case Right(x)=>x 
      }

  val c = "Th"
 val my_country = getCountry(c.toLowerCase.trim, allCountries).toSet
  print( my_country.size match {
   case 2 => my_country.collect{
        case Right(x)=>x 
      }
   case 1 => my_country.collect{
        case Left(x)=>x 
      }
 })

 
/*
  val country_test = allCountries(4)
  val list_test = country_test.findAirports(allAirports)
  println("Airports in ")
  println(country_test.name)
  list_test.foreach(println)*/

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

  def getCountry(country_in : String, countries: List[Country]) : List[Either[String,Country]]= {
    if(country_in.length == 2) 
      countries.map(country => country.code.toLowerCase.trim
                 match {
                  case `country_in` => Right(country)
                  case _ => Left("Can not find this country")
                })
    else
       countries.map(country => country.name.toLowerCase.trim
                 match {
                  case `country_in` => Right(country)
                  case _ => Left("Can not find this country")
                })
              } 
}

 