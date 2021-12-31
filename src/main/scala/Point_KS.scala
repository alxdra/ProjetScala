case class Point(x: Int, y: Int)
object Point{
    def apply(strpoint: String): Option[Point]={
    strpoint.split(",")
            .map(_.trim) match {
        case Array(x,y) => Some(Point(x.toInt, y.toInt))
        case _ => None
        }
    }
 }