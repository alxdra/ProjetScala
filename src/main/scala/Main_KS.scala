import scala.io.Source

object Main extends App {
  //ouvrir
  val bufferedSource = io.Source.fromFile("points.csv")
//mettre en ligne :  bufferedSource.getLines.toList
// https://alvinalexander.com/scala/csv-file-how-to-process-open-read-parse-in-scala/

  bufferedSource.close
}