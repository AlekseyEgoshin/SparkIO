package ru.ibs.source.handlers

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.source.DataStorageTrait
import ru.ibs.workflow.Application

final case class Hadoop(
    uploadType: String,
    schema: String,
    table: String,
    query: Option[String] = Option(""))(implicit app: Application, appManager: ApplicationManager)
    extends DataStorageTrait {

  val fullTableName = s"$schema.$table"

  override def read(): DataFrame = {
    query match {
      case Some(value) => app.spark.sql(value)
      case None => app.spark.read.table(fullTableName)
    }
  }

  override def write(dataFrame: DataFrame): Unit = println("Write Into Hadoop")
}
