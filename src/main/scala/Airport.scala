import scala.util.Try
case class Airport(  id : Int, 
                    identity : String, 
                    airport_type : String,
                    name : String, 
                    latitude_deg : Option[Float],
                    longitude_deg : Option[Float],
                    elevation_ft : Option[Int],
                    continent : Option[String],
                    iso_country : String,
                    iso_region: Option[String],
                    municipality: Option[String],
                    scheduled_service: Option[String], //Boolean ?
                    gps_code: Option[String],
                    iata_code: Option[String],
                    local_code: Option[String],
                    home_link: Option[String],
                    wikipedia_link: Option[String],
                    keywords: Option[String]
                )

object Airport{
    def fromCSV(line : Array[String]): Either[String, Airport] = {
            (Try(line(0).toInt).toOption,  
            line.lift(1), 
            line.lift(2), 
            line.lift(3), 
            Try(line(4).toFloat).toOption, 
            Try(line(5).toFloat).toOption, 
            Try(line(6).toInt).toOption,
            line.lift(7), //continent 
            line.lift(8), //country
            line.lift(9).flatMap(x => if(x.isEmpty) None else Some(x)), //region
            line.lift(10).flatMap(x => if(x.isEmpty) None else Some(x)),//municipaliy 
            line.lift(11).flatMap(x => if(x.isEmpty) None else Some(x)), //service
            line.lift(12).flatMap(x => if(x.isEmpty) None else Some(x)), //gps
            line.lift(13).flatMap(x => if(x.isEmpty) None else Some(x)), //iata
            line.lift(14).flatMap(x => if(x.isEmpty) None else Some(x)), //local code
            line.lift(15).flatMap(x => if(x.isEmpty) None else Some(x)),//homelink
            line.lift(16).flatMap(x => if(x.isEmpty) None else Some(x)),//wiki
            line.lift(17).flatMap(x => if(x.isEmpty) None else Some(x)) //keywords
            )
             match{
                 case (Some(id), Some(identity), Some(airport_type), Some(name), latitude_deg, longitude_deg, elevation_ft, 
                            continent, Some(country), region, municipality, scheduled_service, gps, iata_code,
                            local_code, home_link, wiki_link, keyword)  => 
                     Right(Airport(id, identity, airport_type,name, latitude_deg, longitude_deg, elevation_ft,  continent, country, region, municipality, scheduled_service, gps, iata_code,
                            local_code, home_link, wiki_link, keyword))
                case _ => Left("Error to build airport")
            }
    }
   /* def checkLine[T](tab : Array[T]): Array[T] = { 
        tab.map(x) match{
            case Int => Some(x)
            case _ => None
        }
    }*/
}

