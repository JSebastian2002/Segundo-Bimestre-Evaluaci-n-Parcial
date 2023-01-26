import com.github.tototoshi.csv.CSVReader
import play.api.libs.json.Json

import java.io.File

val reader = CSVReader.open(new File("/Users/andreflores/Downloads/data_parcial_2_OO.csv"))
val data = reader.allWithHeaders()
reader.close()


val nTorneos = data.size

println("El número de torneos en 2022 fue " + nTorneos)

println("El nombre de los torneos jugados en 2022 es")
data.map(x => x("tourney_name")).filter(!_.equals("")).distinct

val prom = (valores : List[Double]) => valores.sum.toDouble/valores.length

//Análisis Exploratorio de Datos

val playersinfo = data.map(row => row("players_info")).takeWhile(x => x != null).map(text => Json.parse(text))
val hand = playersinfo.flatMap(_ \\ "hand").groupBy {
  case nombre => nombre
}.map(nombre => (nombre._1, nombre._2.size)).toList.sortBy(_._2)
println("Mano más usada: " + hand.maxBy(_._2)._1)
println("Mano menos usada: " + hand.minBy(_._2)._1)

val height = playersinfo.flatMap(_ \\ "height").groupBy {
  case nombre => nombre
}.map {
  case nombre => (nombre._1, nombre._2.size)
}.toList.sortBy(_._2)
println("Altura más común: " + height.maxBy(_._2)._1)
println("Altura menos común: " + height.minBy(_._2)._1)

val matchinfo = data.map(row => row("match_info")).filter(!_.equals("")).map(text => Json.parse(text))
val bestOf = matchinfo.flatMap(_ \\ "best_of").groupBy {
  case nombre => nombre
}.map {
  case nombre => (nombre._1, nombre._2.size)
}.toList.sortBy(_._2)
println("Mayor best of: " + bestOf.maxBy(_._2)._1)
println("Menor best of: " + bestOf.minBy(_._2)._1)


val minutes = matchinfo.flatMap(_ \\ "minutes").groupBy {
  case nombre => nombre
}.map {
  case nombre => (nombre._1, nombre._2.size)
}.toList.sortBy(_._2)

println("Cantidad de minutos más repetida: " + minutes.maxBy(_._2))
println("Cantidad de minutos menos repetida: " + minutes.minBy(_._2))
/*
Histogram(hand)
  .title("Manos más usadas")
  .write(new File("/Users/andreflores//Downloads/hand.png"))



 */
val d4 = playersinfo.flatMap(_ \\ "age").groupBy {
  case nombre => nombre
}.map {
  case nombre => (nombre._1, nombre._2.size)
}.toList.sortBy(_._2)
println("Mano más usada: " + d4)