package ru.ibs.source.handlers

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.source.DataStorageTrait
import ru.ibs.workflow.Application

case class File(uploadType: String, path: String, filetype: String)(implicit
    app: Application,
    appManager: ApplicationManager)
    extends DataStorageTrait {

  private val fileStorage =
    if (path.contains(":"))
      path.split(":").head match {
        case "hdfs" => "hdfs"
        case "s3" => "s3"
        case _ => "local"
      }
    else "local"

  override def read(): DataFrame = super.read()

  override def write(df: DataFrame): Unit = ???
}
