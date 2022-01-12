sealed abstract class Point{
}

case class Point2D(x: Float, y:Float) extends Point
case class Point3D(x:Float, y :Float, z:Float) extends Point

object Point{
    def fromCSV(lines : Array[Either[NumberFormatException,Float]]): Either[String, Point] = {
        lines
             match{
                case Array(Right(x),Right(y),Right(z)) => Right(Point3D(x, y, z))
                case Array(Right(x),Right(y)) => Right(Point2D(x, y))
                case _ => Left("Error")
            }
    }
}
//Verif si x, y, z sont bien des float

