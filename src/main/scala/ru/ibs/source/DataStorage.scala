package ru.ibs.source

import org.apache.spark.sql.DataFrame
import org.json4s.{DefaultFormats, Formats, JValue}
import ru.ibs.meta.ApplicationManager
import ru.ibs.source.handlers.{File, Hadoop, Jdbc}
import ru.ibs.workflow.Application

class DataStorage(sourceType: String, json: JValue)(implicit app: Application) {
  implicit private val formats: Formats = DefaultFormats

  println(s"Upload type $sourceType")
  val storage: DataStorageTrait = sourceType.toLowerCase match {
    case "jdbc" => json.extract[Jdbc]
    case "file" => json.extract[File]
    case "hadoop" => json.extract[Hadoop]
    case _ =>
      throw new IllegalArgumentException(
        "Source argument does not have upload type. Please, check it.")
  }

  def read(implicit app: Application, appManager: ApplicationManager): DataFrame = {
    storage.read()
  }

  def write(df: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit = {
    storage.write(df)
  }
}