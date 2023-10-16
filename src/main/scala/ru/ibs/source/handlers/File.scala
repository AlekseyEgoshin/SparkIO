package ru.ibs.source.handlers

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.Application
import ru.ibs.source.DataStorageTrait
import ru.ibs.workflow.ApplicationManager

case class File(
                 uploadType: String,
                 path: String,
                 filetype: String,
                 delimiter: Option[String] = Option(";"),
                 format: Option[String]
               )
    extends DataStorageTrait {

  private val config: Map[String, String] =
    Map[String, String]()

  override def read()(implicit app: Application, appManager: ApplicationManager): DataFrame =
    if (path.contains(":"))
      path.split(":").head match {
        case "hdfs" =>
          app.spark.read.options(config).option("header", "true").option("delimiter", delimiter.get).csv(path)
        case "s3" =>
          app.spark.emptyDataFrame
        case _ =>
          app.spark.read.options(config).option("header", "true").option("delimiter", ";").csv(path)
      }
    else app.spark.emptyDataFrame

  override def write(df: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit =
    df.write.option("header", "true").csv(path)
}
