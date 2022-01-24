import scala.annotation.meta.getter
import scala.util.Try
case class Country(id : Int,
             code : String,
             name : String,
             continent : String,
             wikipedia_link : String,
             keywords : List[String] //list
            )
//Id en value class ?
object Country  {
    def fromCSV[A](line : Array[String]): Either[String, Country] = {
        (Try(line(0).toInt).toOption, Try(line(1)).toOption,Try(line(2)).toOption, Try(line(3)).toOption, Try(line(4)).toOption, Try(line(5)).toOption, Try(line(6)).toOption)
             match{
                case (Some(id), Some(code), Some(name), Some(continent), Some(wiki_link), Some(k1), Some(k2)) =>
                        Right(Country(id,code, name, continent, wiki_link, List(k1,k2)))

                case (Some(id), Some(code), Some(name), Some(continent), Some(wiki_link), Some(k1), None) =>
                        Right(Country(id,code, name, continent, wiki_link, List(k1)))

                case (Some(id), Some(code), Some(name), Some(continent), Some(wiki_link), None, None) =>
                        Right(Country(id,code, name, continent, wiki_link, Nil))

                case _ => Left("Error to build country")
            }
    }
}
