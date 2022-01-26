import scala.util.Try
case class Airport(  id : Int, 
                    identity : String, 
                    airport_type : String,
                    name : String, 
                    latitude_deg : Option[Float],
                    longitude_deg : Option[Float],
                    elevation_ft : Option[Int],
                    continent : String,
                    iso_country : String,
                    iso_region: String,
                    municipality: String,
                    scheduled_service: Option[String], //Boolean ?
                    gps_code: Option[String],
                    iata_code: Option[String],
                    local_code: Option[String],
                    home_link: Option[String],
                    wikipedia_link: Option[String],
                    keywords: Option[String]
                )

object Airport{
    def fromCSV[A](line : Array[String]): Either[String, Airport] = {
            (Try(line(0).toInt).toOption,  
            line.lift(1), 
            line.lift(2), 
            line.lift(3), 
            Try(line(4).toFloat).toOption, 
            Try(line(5).toFloat).toOption, 
            Try(line(6).toInt).toOption,
            line.lift(7), 
            line.lift(8), 
            line.lift(9), 
            line.lift(10),
            line.lift(11),
            line.lift(12), 
            line.lift(13), 
            line.lift(14), 
            line.lift(15),
            line.lift(16),
            line.lift(17)
            ) 
             match{
                 case (Some(id), Some(identity), Some(airport_type), Some(name), Some(latitude_deg), Some(longitude_deg), Some(elevation_ft), 
                            Some(continent), Some(country), Some(region), Some(municipality), Some(scheduled_service), Some(gps), Some(iata_code),
                            Some(local_code), Some(home_link), Some(wiki_link), Some(keyword))  => 
                     Right(Airport(id, identity, airport_type,name, Some(latitude_deg), Some(longitude_deg), Some(elevation_ft),  continent, country, region, municipality, Some(scheduled_service), Some(gps), Some(iata_code),
                            Some(local_code), Some(home_link), Some(wiki_link), Some(keyword)))

                    case _ => Left("Error to build airport")
            }
    }
    def checkLine[T](tab : Array[T]): Array[T] = { 
        tab.map(x) match{
            case Int => Some(x)
            case _ => None
        }
    }
}

