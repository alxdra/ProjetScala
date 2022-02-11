import scala.io.Source
import javax.management.monitor.CounterMonitor

object Main extends App {
  val allCountries = readCSV("data/countries.csv",Country.fromCSV).collect{
        case Right(x)=>x
      }
  
  val allAirports = readCSV("data/airports.csv", Airport.fromCSV).collect{
        case Right(x)=>x 
      }

  val allRunways = readCSV("data/runways.csv", Runway.fromCSV).collect{
        case Right(x)=>x 
      }

              
  displayAirports("France")

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

  
  def getCountry(country_in : String) : List[Either[String,Country]]= {
    if(country_in.length == 2) 
      allCountries.map(country => country.code.toLowerCase.trim
                 match {
                  case `country_in` => Right(country)
                  case _ => Left("Can not find this country")
                })
    else
       allCountries.map(country => country.name.toLowerCase.trim
                 match {
                  case `country_in` => Right(country)
                  case _ => Left("Can not find this country")
                })
              } 

  def displayAirports(input : String)={
    val my_country = getCountry(input.toLowerCase.trim).toSet

    my_country.size match {
    case 2 => my_country.collect{
          case Right(x)=>x.findAirports(allAirports)
                          .map(a=>
                            (a.name,
                            a.findRunways(allRunways)
                              .map(runway => runway.id)))
                          .map(println)
        }
    case 1 => my_country.collect{
          case Left(x)=>print(x)
        }
    } 
  }
}

 