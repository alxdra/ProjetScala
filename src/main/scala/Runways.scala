
import scala.util.Try
case class Runway ( id : Int, 
                airport_ref: Int,
                airport_ident: String,
                length_ft: Option[Int],
                width_ft: Option[Int],
                surface: String,
                lighted: Boolean,
                closed: Boolean,
                le_ident: Option[String],
                le_latitude_deg: Option[Float],
                le_longitude_deg: Option[Float],
                le_elevation_ft: Option[Int],
                le_heading_degT: Option[Float],
                le_displaced_threshold_ft: Option[Int],
                he_ident: Option[String],
                he_latitude_deg: Option[Float],
                he_longitude_deg : Option[Float],
                he_elevation_ft: Option[Int],
                he_heading_degT: Option[Float],
                he_displaced_threshold_ft : Option[Int]
            ) 

object Runway{
 def fromCSV[A](line : Array[String]): Either[String, Runway] = {
     (  Try(line(0).toInt).toOption,  //id
        Try(line(1).toInt).toOption,  //ref
        line.lift(2),  //airport ident
        Try(line(3).toInt).toOption,  //lenght
        Try(line(4).toInt).toOption, //width    
        line.lift(5),  //surface
        line.lift(6).flatMap(x => if(x==1) Some(true) else Some(false)), //lighted
        line.lift(7).flatMap(x => if(x==1) Some(true) else Some(false)), //closed

        line.lift(8).flatMap(x => if(x.isEmpty) None else Some(x)), //LE
        Try(line(9).toFloat).toOption,//lat
        Try(line(10).toFloat).toOption,//long
        Try(line(11).toInt).toOption,//ele
        Try(line(12).toFloat).toOption,//heading
        Try(line(13).toInt).toOption,//thre

        line.lift(14).flatMap(x => if(x.isEmpty) None else Some(x)), //HE
        Try(line(15).toFloat).toOption,
        Try(line(16).toFloat).toOption,
        Try(line(17).toInt).toOption,
        Try(line(18).toFloat).toOption,
        Try(line(19).toInt).toOption
    )
        match{
                case (Some(id),Some(airport_ref), Some(airport_ident), length_ft, width_ft, Some(surface), Some(lighted), Some(closed), 
                le_ident, le_latitude_deg, le_longitude_deg, le_elevation_ft, le_heading_degT, le_displaced_threshold_ft,
                he_ident, he_latitude_deg, he_longitude_deg, he_elevation_ft, he_heading_degT, he_displaced_threshold_ft) =>

                Right(Runway(id ,airport_ref, airport_ident, length_ft, width_ft, surface, lighted, closed, 
                le_ident, le_latitude_deg, le_longitude_deg, le_elevation_ft, le_heading_degT, le_displaced_threshold_ft,
                he_ident, he_longitude_deg, he_latitude_deg, he_elevation_ft, he_heading_degT, he_displaced_threshold_ft))
            
                case _=> Left("Error to build runway")
        }
    }
}