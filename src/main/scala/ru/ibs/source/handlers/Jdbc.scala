package ru.ibs.source.handlers

import org.apache.spark.sql.DataFrame
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
    jdbcDialect: Option[String])(implicit app: Application, appManager: ApplicationManager)
    extends DataStorageTrait {

  override def read(): DataFrame = {
    val config = getConfig
    app.spark.read.format("jdbc").options(config).load()
  }

  override def write(dataFrame: DataFrame): Unit =
    println("JDBC")

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
