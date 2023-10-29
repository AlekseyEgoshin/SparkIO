package ru.ibs.source

import org.apache.spark.sql.DataFrame
import ru.ibs.workflow.{Application, ApplicationManager}

trait DataStorageTrait {
  def read()(implicit app: Application, appManager: ApplicationManager): DataFrame
  def write(df: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit
}
