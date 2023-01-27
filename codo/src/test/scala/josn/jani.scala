package josn

import breeze.linalg.sum

import scala.util.Try
import com.github.tototoshi.csv.CSVReader
import org.apache.spark.sql.functions._

import java.io.File
import scala.Option.when
// De acuerdo al nivel del torneo cuantos surdos y cuantos diestros ganaron algun partido?

object jani extends App {
  val reader = CSVReader.open(new File("/home/frank/Documentos/tercero/Programacion/ProyectoIntegrador/codo/data_parcial_2_OO.csv"))
  val data = reader.allWithHeaders()
  reader.close()
  //Edad del jugador que quedo en la posicion tres en la posicion de ranking , y el puntaje del encuentro

  val resultados = data
    .groupBy("tourney_level")
    .agg(
      sum(when(col("resultado") == "winner_id" && col("hand") === "diestro", 1).otherwise(0. _)).alias("diestros_ganadores"),
      sum(when(col("resultado") == "winner_id" && col("hand") === "surdo", 1).otherwise(0)).alias("surdos_ganadores")
    )

  resultados.show()

}
