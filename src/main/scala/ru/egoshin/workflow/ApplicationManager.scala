package ru.egoshin.workflow

import org.json4s.{DefaultFormats, Formats, JValue}
import ru.egoshin.source.DataStorage
import ru.egoshin.utils.JsonParser

class ApplicationManager(arguments: Map[String, String])(implicit app: Application) {
  def getConnector(params: Map[String, String], conType: String): DataStorage = {
    println(s"Trying to get $conType from params")
    if (params.contains("source")) {
      val sourceJson: JValue = JsonParser.parseFromString(params.getOrElse(conType, ""))
      val sourceType: String = (sourceJson \\ "uploadType").extract[String]

      new DataStorage(sourceType, sourceJson)
    } else throw new IllegalArgumentException(s"Input data does not have '$conType' block.")
  }

  implicit val formats: Formats = DefaultFormats

  val source: DataStorage = getConnector(arguments, "source")

  val target: DataStorage = getConnector(arguments, "target")
}
