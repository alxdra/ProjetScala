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
        line(8).toOption,//LE 
        line.lift(9).flatMap(x => if(x.isEmpty) None else Some(x)), 
        Try(line(10).toFloat).toOption,
        Try(line(11).toFloat).toOption,
        Try(line(12).toInt).toOption,
        Try(line(13).toFloat).toOption,
        Try(line(14).toInt).toOption,
        line(15).toOption,//HE
        line.lift(16).flatMap(x => if(x.isEmpty) None else Some(x)), 
        Try(line(17).toFloat).toOption,
        Try(line(18).toFloat).toOption,
        Try(line(19).toInt).toOption,
        Try(line(20).toFloat).toOption,
        Try(line(21).toInt).toOption
    )
    match 
        {       
            case (Some(id),Some(airport_ref), Some(airport_ident), Some(length_ft), Some(width_ft), Some(surface), Some(lighted), Some(lighted), Some(closed), 
            le_ident, le_latitude_deg, le_longitude_deg, le_elevation_ft, le_heading_degT, le_displaced_threshold_ft, he_ident, he_longitude_deg, he_latitude_deg, he_elevation_ft, he_heading_degT, he_displaced_threshold_ft) =>
                Right()

            case _ => Left("Error to build runway")
        }
    }
}