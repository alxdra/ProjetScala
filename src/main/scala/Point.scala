case class Point(x: Float, y:Float, z:Float = 0)

object Point{
    def apply(st : String): Option[Point] = {
        st.split(",")
            .map(_.trim) //trim retire les espaces 
             match{
                case Array(x,y,z) => Some(Point(x.toFloat, y.toFloat, z.toFloat))
                case Array(x,y) => Some(Point(x.toFloat, y.toFloat))
                case _ => None
            }
    }
}