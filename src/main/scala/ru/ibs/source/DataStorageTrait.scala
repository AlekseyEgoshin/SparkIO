package ru.ibs.source

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.workflow.Application

trait DataStorageTrait {
  def read()(implicit app: Application, appManager: ApplicationManager): DataFrame
  def write(df: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit
}
