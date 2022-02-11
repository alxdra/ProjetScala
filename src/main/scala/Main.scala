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

 
//MENU
  commandlineuser(allAirports,allRunways,allCountries)

  def commandlineuser(Airport : List[Airport] , Runways: List[Runway] , Country : List[Country]) : Any = {
    println("------------------------------------------")
    println("Que voulez-vous faire ?(entrez un numero")
    println("1. query")
    println("2. reports")
    println("3. exit")
    scala.io.StdIn.readLine() match {
      case "1" => commandlinequery(allAirports,allRunways,allCountries)
      case "2" => commandlinereports(allAirports,allRunways,allCountries)
      case "3" => println("exit")
      case _ => println("error")
    }
  }

  def commandlinequery(Airport : List[Airport] , Runways: List[Runway] , Country : List[Country]) : Any = { 
    println("Entrez un nom de pays ou code. Ex : 'Andorra' ou 'AD', 'France' ou 'FR' ")
    displayAirports(scala.io.StdIn.readLine())
    
    commandlineuser(allAirports,allRunways,allCountries)

  }

  def commandlinereports(Airport : List[Airport] , Runways: List[Runway] , Country : List[Country]) : Any = { 
    println("1. 10 countries with highest number of airports (with count) and countries  with lowest number of airports.")
    println("2. Type of runways per country")
    println("3. The top 10 most common runway latitude")

    scala.io.StdIn.readLine() match {
     //case "1" => 
      //case "2" => 
      case "3" => topCountries()
      case _ => println("error")
    }
      commandlineuser(allAirports,allRunways,allCountries)

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

 
