case class Point2(x: Float, y:Float)
object Point2{
    def apply(strpoint: String): Option[Point2]={
    strpoint.split(",")
            .map(_.trim) 
            match {
                case Array(x,y) => Some(Point2(x.toFloat, y.toFloat))
                case _ => None
            }
    }
 }
