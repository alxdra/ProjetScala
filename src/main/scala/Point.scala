sealed abstract class Point{
}


case class Point2D(x: Float, y:Float) extends Point
case class Point3D(x:Float, y :Float, z:Float) extends Point

object Point{
    def apply(st : String): Option[Point] = {
        st.split(",")
            .map(_.trim) //trim retire les espaces 
             match{
                case Array(x,y,z) => Some(Point3D(x.toFloat, y.toFloat, z.toFloat))
                case Array(x,y) => Some(Point2D(x.toFloat, y.toFloat))
                case _ => None
            }
    }
}