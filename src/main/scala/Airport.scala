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
/*
object Airport{
    def fromCSV[A](lines : Array[String]): Either[String, Airport] = {
        lines
             match{
                case Array(id, identity, airport_type,name, latitude_deg, longitude_deg , elevation_ft,
                            continent, country, region, municipality, scheduled_service, gps, iata_code,
                            local_code, home_link, wiki_link, keyword)  => 
                     Right(Airport(id.toInt, identity, airport_type,name, Some(latitude_deg.toFloat), Some(longitude_deg.toFloat), 
                            Some(elevation_ft.toInt),  continent, country, region, municipality, scheduled_service, gps, Some(iata_code),
                            local_code,Some(home_link), Some(wiki_link), Some(keyword)))
                
                case _ => Left("Error to build airport")
            }
    }
}
*/