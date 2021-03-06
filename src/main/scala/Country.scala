import scala.util.Try
case class Country(id : Int, //Id_country,
             code : String,
             name : String,
             continent : String,
             keywords : List[String], //list
           //  airports: Option[List[Airport]]
            ){

        def findAirports(a : List[Airport]): List[Airport]={
            a.foldLeft(List[Airport]())((list, currentAirport)=>{
                currentAirport.iso_country 
                        match {
                        case `code` => currentAirport :: list
                        case _ => list
                }
            })
        }
}

//class Id_country(val x:Int) extends AnyVal
//Id en value class ?
object Country  {
    def fromCSV[A](line : Array[String]): Either[String, Country] = {
        (Try(line(0).toInt).toOption, line.lift(1), line.lift(2), line.lift(3), line.lift(5), line.lift(6))
             match{
                case (Some(id), Some(code), Some(name), Some(continent), Some(k1), Some(k2)) =>
                        //Right(Country(new Id_country(id),code, name, continent, wiki_link, List(k1,k2)))
                        Right(Country(id,code, name, continent, List(k1,k2)))

                case (Some(id), Some(code), Some(name), Some(continent), Some(k1), None) =>
                        Right(Country(id,code, name, continent, List(k1)))

                case (Some(id), Some(code), Some(name), Some(continent), None, None) =>
                        Right(Country(id,code, name, continent, Nil))

                case _ => Left("Error to build country")
            }
    }
}