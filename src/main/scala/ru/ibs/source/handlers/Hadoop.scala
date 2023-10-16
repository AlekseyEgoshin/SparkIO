package ru.ibs.source.handlers

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.Application
import ru.ibs.source.DataStorageTrait
import ru.ibs.workflow.ApplicationManager

final case class Hadoop(uploadType: String, schema: String, table: String, query: Option[String])
    extends DataStorageTrait {

  val fullTableName = s"$schema.$table"

  override def read()(implicit app: Application, appManager: ApplicationManager): DataFrame = {
    query match {
      case Some(value) => app.spark.sql(value)
      case None => app.spark.read.table(fullTableName)
    }
  }

  override def write(dataFrame: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit =
    println("Write Into Hadoop")
}
