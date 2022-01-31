class Runways ( id : Int, 
                airport_ref: Int,
                airport_ident: String,
                length_ft: Int,
                width_ft: Int,
                surface: String,
                lighted: Boolean,
                closed: Boolean,
                le_ident: Option[String],
                le_latitude_deg: Option[Float],
                le_longitude_deg: Option[Float],
                le_elevation_ft: Option[Int],
                le_heading_degT: OPtion[Float],
                le_displaced_threshold_ft: Option[Int],
                he_ident: Option[String],
                he_latitude_deg: Option[Float],
                he_longitude_deg : Option[Float],
                he_elevation_ft: Option[Int],
                he_heading_degT: Option[Float],
                he_displaced_threshold_ft : Option[Int]
            ) 

object Runways{
 def fromCSV[A](line : Array[String]): Either[String, Country] = {
     (  Try(line(0).toInt).toOption,  //id
        Try(line(1).toInt).toOption,  //ref
        line(2).toOption,  //airport ident
        Try(line(3).toInt).toOption,  //lenght
        Try(line(4).toInt).toOption, //width    
        line(5).toOption,  //surface
        line.lift(6).flatMap(x => if(x==1) True else False), //lighted
        line.lift(7).flatMap(x => if(x==1) True else False), //cloded
        line.lift(8).flatMap(x => if(x.isEmpty) None else Some(x)), //LE
        Try(line(9).toFloat).toOption,
        Try(line(10).toFloat).toOption,
        Try(line(11).toInt).toOption,
        Try(line(12).toFloat).toOption,
        Try(line(13).toInt).toOption,
        line.lift(14).flatMap(x => if(x.isEmpty) None else Some(x)), //HE
        Try(line(15).toFloat).toOption,
        Try(line(16).toFloat).toOption,
        Try(line(17).toInt).toOption,
        Try(line(18).toFloat).toOption,
        Try(line(19).toInt).toOption
    )
    match 
        {       
            case (Some(id),Some(airport_ref), Some(airport_ident), Some(length_ft), Some(width_ft), Some(surface), Some(lighted), Some(closed), 
            le_ident, le_latitude_deg, le_longitude_deg, le_elevation_ft, le_heading_degT, le_displaced_threshold_ft, he_ident, he_latitude_deg, he_longitude_deg,
            he_elevation_ft, he_heading_degT, he_displaced_threshold_ft) =>
                Right(Runways(id,airport_ref, airport_ident, length_ft, width_ft, surface, lighted, closed, le_ident, le_latitude_deg, le_longitude_deg, le_elevation_ft, le_heading_degT, le_displaced_threshold_ft,
                he_ident, he_longitude_deg, he_latitude_deg, he_elevation_ft, he_displaced_threshold_ft))

            case _ => Left("Error to build runway")
        }
    }
}