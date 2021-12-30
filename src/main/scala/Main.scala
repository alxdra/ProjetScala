import scala.io.Source

object Main extends App {
 //TODO 
 //Gestion des exeptions à l'ouverture du fichier ? 
 //Gestion si le séparateur n'est pas une virgule ?
 //Gestion si charactères ? 

  
  //Open file and get content 
  val bufferedSource = Source.fromFile("points.csv")
  val fileList = bufferedSource.getLines.toList
  
  //Create List of points
  val ListOfPoints = fileList.map(s => Point.apply(s))
  ListOfPoints.foreach(println)

  //Close the file
  bufferedSource.close
}