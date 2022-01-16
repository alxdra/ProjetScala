case class Airport(  id : Int, 
                    identity : String, 
                    airport_type : String,
                    name : String, 
                    latitude_deg : Float,
                    longitude_deg : Float,
                    elevation_ft : Int,
                    continent : String,
                    iso_country : String,
                    iso_region: String,
                    municipality: String,
                    scheduled_service: Boolean,
                    gps_code: String,
                    iata_code: String,
                    local_code: String,
                    home_link: String,
                    wikipedia_link: String,
                    keywords: String,
                ) extends Country

object Airport{

}