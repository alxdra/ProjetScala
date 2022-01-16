class Runways ( id : Int, 
                airport_ref: Int,
                airport_ident: String,
                length_ft: Int,
                width_ft: Int,
                surface: String,
                lighted: Boolean,
                closed: Boolean,
                le_ident: String,
                le_latitude_deg: Float,
                le_longitude_deg: Float,
                le_elevation_ft: Int,
                le_heading_degT: Float,
                le_displaced_threshold_ft: Int,
                he_ident: String,
                he_latitude_deg: Float,
                he_longitude_deg : Float,
                he_elevation_ft: Int,
                he_heading_degT: Float,
                he_displaced_threshold_ft : Int,
            ) extends Airport

object Runways{

}