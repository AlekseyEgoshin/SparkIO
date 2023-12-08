package ru.egoshin.source

import org.apache.spark.sql.DataFrame
import org.json4s.{DefaultFormats, Formats, JValue}
import ru.egoshin.source.handlers.{File, Hadoop, Jdbc, Kafka}
import ru.egoshin.workflow.{Application, ApplicationManager}

class DataStorage(sourceType: String, json: JValue)(implicit app: Application) {
  implicit private val formats: Formats = DefaultFormats

  println(s"Upload type $sourceType")
  val storage: DataStorageTrait = sourceType.toLowerCase match {
    case "jdbc" => json.extract[Jdbc]
    case "kafka" => json.extract[Kafka]
    case "file" => json.extract[File]
    case "hadoop" => json.extract[Hadoop]
    case _ =>
      throw new IllegalArgumentException(
        "Source argument does not have upload type. Please, check it.")
  }

  def read(implicit app: Application, appManager: ApplicationManager): DataFrame =
    storage.read()

  def write(df: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit =
    storage.write(df)
}
