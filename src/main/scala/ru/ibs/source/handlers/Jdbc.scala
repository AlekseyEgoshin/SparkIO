package ru.ibs.source.handlers

import org.apache.spark.sql.{DataFrame, SaveMode}
import ru.ibs.meta.ApplicationManager
import ru.ibs.source.DataStorageTrait
import ru.ibs.workflow.Application

final case class Jdbc(
    uploadType: String,
    dbType: Option[String],
    url: Option[String],
    login: Option[String],
    password: Option[String],
    driver: Option[String],
    schema: String,
    table: String,
    query: Option[String],
    partitionColumn: Option[String],
    lowerBound: Option[String],
    upperBound: Option[String],
    numPartitions: Option[String],
    jdbcDialect: Option[String])
    extends DataStorageTrait {
  val saveMode = SaveMode.Overwrite

  override def read()(implicit app: Application, appManager: ApplicationManager): DataFrame =
    app.spark.read.format("jdbc").options(getConfig).load()

  override def write(df: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit =
    df.write
      .mode(saveMode)
      .format("jdbc")
      .options(getConfig)
      .save()

  def getConfig: Map[String, String] = Map(
    "driver" -> driver.get,
    "url" -> url.get,
    "user" -> login.get,
    "password" -> password.get,
    "dbtable" -> s"$schema.$table",
    "fetchsize" -> "10000")

  def getConnection: String = {
    s"$url"
  }
}
