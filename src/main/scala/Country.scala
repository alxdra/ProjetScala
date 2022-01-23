import scala.annotation.meta.getter
case class Country(id : Int,
             code : String,
             name : String,
             continent : String,
             wikipedia_link : String,
             keywords : Array[Option[String]]
            )
//Id en value class ?
object Country  {
    def fromCSV[A](lines : Array[String]): Either[String, Country] = {
        lines
             match{
                case Array(id, code, name, continent, wikipedia_link, keyword1, keyword2)
                        => Right(Country(id.toInt,code, name, continent, 
                         wikipedia_link,Array(Some(keyword1),Some(keyword2))))
                case Array(id, code, name, continent, wikipedia_link, keyword1)
                        => Right(Country(id.toInt,code, name, continent, 
                         wikipedia_link, Array(Some(keyword1), None)))
                case Array(id, code, name, continent, wikipedia_link)
                        => Right(Country(id.toInt,code, name, continent, 
                        wikipedia_link, Array(None)))
                case _ => Left("Error to build country")
            }
    }
}