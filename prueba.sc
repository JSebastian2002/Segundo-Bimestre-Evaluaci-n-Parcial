import com.github.tototoshi.csv.CSVReader
import play.api.libs.json.Json

import java.io.File

val reader = CSVReader.open(new File("/Users/andreflores/Downloads/data_parcial_2_OO.csv"))
val data = reader.allWithHeaders()
reader.close()


val nTorneos = data.size

println("El número de torneos en 2022 fue " + nTorneos)

println("El nombre de los torneos jugados en 2022 es : " +
  data.map(x => x("tourney_name")).filter(!_.equals("")).distinct)

data.map(x => x("tourney_level")).filter(!_.equals("")).distinct
val prom = (valores : List[Int]) => valores.sum.toDouble/valores.length
//Análisis Exploratorio de Datos
//hand
val playersinfo = data.map(row => row("players_info")).map(text => Json.parse(text))
val hand = playersinfo.flatMap(_ \\ "hand").groupBy {
  case nombre => nombre
}.map(nombre => (nombre._1, nombre._2.size))
  .toList.sortBy(_._2)
println("Mano más usada: " + hand.maxBy(_._2)._1)
println("Mano menos usada: " + hand.minBy(_._2)._1)

//height

val height = playersinfo.flatMap(_ \\ "height").groupBy {
  case nombre => nombre
}.map {
  case nombre => (nombre._1, nombre._2.size)
}.toList.sortBy(_._2)
println("Altura más común: " + height.maxBy(_._2)._1)
println("Altura menos común: " + height.minBy(_._2)._1)

val height2 = playersinfo.flatMap(_ \\ "height")


//best of y minutes

val matchinfo = data.map(row => row("match_info")).map(text => Json.parse(text)).filter(x => x != null)

val bestOf = matchinfo.flatMap(_ \\ "best_of").groupBy {
  case nombre => nombre
}.map {
  case nombre => (nombre._1, nombre._2.size)
}.toList.sortBy(_._2)
println("Mayor best of: " + bestOf.maxBy(_._2)._1)
println("Menor best of: " + bestOf.minBy(_._2)._1)


val minutes = matchinfo.flatMap(_ \\ "minutes").map(_.toString()).groupBy {
  case nombre => nombre
}.map {
  case nombre => (nombre._1, nombre._2.size)
}.toList.sortBy(_._2).reverse

println("Cantidad de minutos más usual: " + minutes.maxBy(_._2)._1)
println("Cantidad de minutos menos usual: " + minutes.minBy(_._2)._1)





