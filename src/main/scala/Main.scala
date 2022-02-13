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

  commandlineuser(allAirports,allRunways,allCountries)

   def commandlineuser(airports : List[Airport] , runways: List[Runway] , countries : List[Country]) : Any = {
    if(airports.isEmpty) println("Error to get Airports")
    else if(runways.isEmpty) println("Error to get Runways")
    else if(countries.isEmpty) println("Error to get Countries")
    else {
        println("\n \n ------------------MAIN MENU------------------------ \n")
        println("Que voulez-vous faire ?(entrez un numero)")
        println("1. Query")
        println("2. Reports")
        println("3. Exit")
        println("\n ==> \n ")
        scala.io.StdIn.readLine() match {
          case "1" | "Query" | "query" => commandlinequery(airports,runways,countries)
          case "2" | "Reports" | "reports" => commandlinereports(airports,runways,countries)
          case "3" | "Exit" | "exit" => println("... Exit ... \n \n")
          case _ => println("ERROR. Enter a correct option.\n")
            commandlineuser(airports,runways,countries)
        }
    }
  }

def commandlinequery(airports : List[Airport] , runways: List[Runway] , countries : List[Country]) : Any = {
    println("\n \n ------------------QUERY MENU------------------------ \n")
    println("--Query--")
    println("1. Display the airports & runways at each airport for a country")
    println("2. Back to menu")
    println("3. Exit")
    println("\n ==> \n ")
     scala.io.StdIn.readLine() match {
      case "1" | "Display"  => println("Entrez un nom de pays ou code. Ex : 'Andorra' ou 'AD', 'France' ou 'FR' ")
        println("==>")
        displayAirports(scala.io.StdIn.readLine(), airports,runways,countries)
        commandlinequery(airports,runways,countries)
      case "2" | "Back" | "Back to menu" => println("... Going back to menu ...")
        commandlineuser(airports,runways,countries)
      case "3" | "Exit" | "exit" => println("... Exit ... \n \n")
      case _ => println("ERROR. Enter a correct option. \n")
        commandlinequery(airports,runways,countries)
    }
}

  def commandlinereports(airports : List[Airport] , runways: List[Runway] , countries: List[Country]) : Any = { 
    println("\n \n ------------------REPORTS MENU------------------------ \n")
    println("--Reports--")
    println("Quelle requête voulez-vous faire ? ")
    println("1. Top10 country : 10 countries with highest number of airports ")
    println("2. Top 10  countries  with lowest number of airports.")
    println("3. Runways type : Type of runways per country")
    println("4. Top10 runways : The top 10 most common runway latitude")
    println("5. Back to menu")
    println("6. Exit")
    println("\n ==> \n ")

    scala.io.StdIn.readLine() match {
      case "1" | "highest" => println("\n ---RESULTS-- \n")
        topCountries(airports,countries)
        commandlinereports(airports,runways,countries)
      case "2" |  "lowest" => println("\n ---RESULTS-- \n")
        lowCountries(airports,countries)
        commandlinereports(airports,runways,countries)
      case "3" | "Runways type" | "runways type" =>  println("\n ---RESULTS-- \n")
        typeRunways(airports,runways,countries)
        commandlinereports(airports,runways,countries)
      case "4" | "Top10 runways" | "top10 runways" => println("\n ---RESULTS-- \n")
        commonLatitud(runways)
        commandlinereports(airports,runways,countries)
      case "5"| "Back" | "Back to menu" => println("... Going back to menu ... \n ")
       commandlineuser(airports,runways,countries)
      case "6" | "Exit" | "exit" => println("... Exit ... \n \n")
      case _ => println("Error, enter a correct option. \n")
        commandlinereports(airports,runways,countries)
    }
  }


  def readCSV[T](fileName :String, 
                fonctionObject : Array[String]=> Either[String,T], 
                )  : List[Either[String,T]]= {

    val bufferedSource = Source.fromFile(fileName)
    val fileList = bufferedSource.getLines
      .toList
      .drop(1)
      .map(_.split(",")
      .map(_.trim))
      .map(_.map(_.replaceAll("^\"|\"$", "")))
      .map(fonctionObject)
                 
    bufferedSource.close
    return fileList
  }
  
  def getCountry(input : String, countries : List[Country]) : Either[String,Country]= {
        val country_in = input.toLowerCase.trim
        if(country_in.length == 2) {
          countries.filter( _.code
                                  .toLowerCase
                                  .trim == `country_in`)
                      match{
                                  case x if(x.length == 1) => Right(x.head)
                                  case Nil => Left("Can not find this country")
                      }
          }
          else{
            countries.filter(x=>fuzzyMatching(x.name, `country_in`) ==true)
                           match{
                              case x if(x.length == 1) => Right(x.head)
                              case Nil => Left("Can not find this country")
                          }
            }
  }
  
 def displayAirports(input : String, airports : List[Airport], runways :List[Runway], countries : List[Country])={
     val my_country = getCountry(input, countries)
          my_country match {
            case Right(x) => x.findAirports(airports)
                              .map(a=>
                                  ( a.name,
                                    a.findRunways(runways)
                                    .map(runway => runway.id)))
                              .map(println)
              case Left(x)=>print(x)        
        }    
  }
    
  def topCountries(airports : List[Airport], countries : List[Country]):Any={
    countries.map(country =>  (country.name,country.findAirports(airports).length))
              .sortBy(x => x._2)
              .takeRight(10)
              .reverse
              .map(println)                
  }

  def lowCountries(airports : List[Airport], countries : List[Country]):Any={
    countries.map(country =>  (country.name, country.findAirports(airports).length))
              .sortBy(x => x._2)
              .takeWhile(_._2< 2)
              .map(println)                   
  }

  def commonLatitud(runways : List[Runway]):Any={
    runways.map(r => r.le_ident)
            .groupBy(identity)
            .map(t=> (t._1, t._2.length))
            .toList
            .sortBy(x => x._2)
            .takeRight(10)
            .reverse
            .map(println)                      
  }

  def typeRunways( airports : List[Airport], runways :List[Runway], countries : List[Country])={
   countries.map(c=> (c.name, c.findAirports(allAirports)
                                .map(a=>{ a.findRunways(allRunways) match{
                                                                    case x::tail => x.surface
                                                                    case Nil => None
                                                                  }                                                                
                                          })
                                .toSet
                                .filter(_!=None)))
              .map(println)
  }

  def fuzzyMatching(country_name: String , input: String): Boolean={
    val name = country_name.toLowerCase.trim
    if(name.equals(`input`)) true
    else if (name.toSet.equals(input.toSet) || name.containsSlice(`input`)== true){
      println("\n Did you mean : ")
      print(name)
      print(" ? yes / no \n\n")

      scala.io.StdIn.readLine() match {
        case "yes" | "y" | "1" => true
        case _ => false
      }
    }
    else false
  }
}
