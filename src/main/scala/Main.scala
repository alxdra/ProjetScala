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
    println("\n \n ------------------MAIN MENU------------------------ \n")
    println("Que voulez-vous faire ?(entrez un numero)")
    println("1. Query")
    println("2. Reports")
    println("3. Exit")
    println("\n ==> \n ")
    scala.io.StdIn.readLine() match {
      case "1" | "Query" | "query" => commandlinequery(allAirports,allRunways,allCountries)
      case "2" | "Reports" | "reports" => commandlinereports(allAirports,allRunways,allCountries)
      case "3" | "Exit" | "exit" => println("... Exit ... \n \n")
      case _ => println("ERROR. Enter a correct option.\n")
        commandlineuser(allAirports,allRunways,allCountries)
    }
  }

  def commandlinequery(Airport : List[Airport] , Runways: List[Runway] , Country : List[Country]) : Any = {
    println("\n \n ------------------QUERY MENU------------------------ \n")
    println("--Query--")
    println("1. Display the airports & runways at each airport for a country")
    println("2. Back to menu")
    println("3. Exit")
    println("\n ==> \n ")
     scala.io.StdIn.readLine() match {
      case "1" | "Display"  => println("Entrez un nom de pays ou code. Ex : 'Andorra' ou 'AD', 'France' ou 'FR' ")
        println("==>")
        displayAirports(scala.io.StdIn.readLine())
        commandlinequery(allAirports,allRunways,allCountries)
      case "2" | "Back" | "Back to menu" => println("... Going back to menu ...")
        commandlineuser(allAirports,allRunways,allCountries)
      case "3" | "Exit" | "exit" => println("... Exit ... \n \n")
      case _ => println("ERROR. Enter a correct option. \n")
        commandlinequery(allAirports,allRunways,allCountries)
    }
    

  }

  def commandlinereports(Airport : List[Airport] , Runways: List[Runway] , Country : List[Country]) : Any = { 
    println("\n \n ------------------REPORTS MENU------------------------ \n")
    println("--Reports--")
    println("Quelle requête voulez-vous faire ? ")
    println("1. Top10 country : 10 countries with highest number of airports (with count) and countries  with lowest number of airports.")
    println("2. Runways type : Type of runways per country")
    println("3. Top10 runways : The top 10 most common runway latitude")
    println("4. Back to menu")
    println("5. Exit")
    println("\n ==> \n ")

    scala.io.StdIn.readLine() match {
      case "1" | "Top10 country" | "top10 country"=> println("\n ---RESULTS-- \n")
        topCountries()
        commandlinereports(allAirports,allRunways,allCountries)
      case "2" | "Runways type" | "runways type" =>  println("\n ---RESULTS-- \n")
        typeRunways()
        commandlinereports(allAirports,allRunways,allCountries)
      case "3" | "Top10 runways" | "top10 runways" => println("\n ---RESULTS-- \n")
        commonLatitud()
        commandlinereports(allAirports,allRunways,allCountries)
      case "4"| "Back" | "Back to menu" => println("... Going back to menu ... \n ")
       commandlineuser(allAirports,allRunways,allCountries)
      case "5" | "Exit" | "exit" => println("... Exit ... \n \n")
      case _ => println("Error, enter a correct option. \n")
        commandlinereports(allAirports,allRunways,allCountries)
    }



  }
  
  


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


  
  
  def getCountry(country_in : String) : Either[String,Country]= {
    if(country_in.length == 2) {
      allCountries.dropWhile( _.code
                               .toLowerCase
                               .trim != `country_in`)
                  .take(1) match{
                              case x if(x.length == 1) => Right(x.head)
                              case Nil => Left("Can not find this country")
                  }
    }
    else{
       allCountries.dropWhile( _.name
                                .toLowerCase
                                .trim
                                .containsSlice(`country_in`) !=true)
                    .take(1) match{
                        case x if(x.length == 1) => Right(x.head)
                        case Nil => Left("Can not find this country")
                    }
      }
    }
  

 def displayAirports(input : String)={
    val my_country = getCountry(input.toLowerCase.trim)
    my_country match {
      case Right(x) => x.findAirports(allAirports)
                        .map(a=>
                            ( a.name,
                              a.findRunways(allRunways)
                               .map(runway => runway.id)))
                        .map(println)
        case Left(x)=>print(x)        
    } 
  }
  
  def topCountries()={
    allCountries.map(country =>  (country.name,country.findAirports(allAirports).length))
                      .sortBy(x => x._2)
                      .takeRight(10)
                      .map(println)                     
  }
  def lowCountries()={
    allCountries.map(country =>  (country.name,country.findAirports(allAirports).length))
                      .sortBy(x => x._2)
                      .take(10)
                      .map(println)                     
  }

  def commonLatitud()={
    allRunways.map(r => r.le_ident)
              .groupBy(l=>l)
              .map(t=> (t._1, t._2.length))
              .map(println)
  }
  
  def typeRunways()={

    allCountries.map(c=> (c.name, c.findAirports(allAirports)
                          .map(a=>
                            a.findRunways(allRunways)
                              .map(runway => runway.surface))))
                          .map(println) 
  }
  
  
}

 
