package ru.egoshin.source

import org.apache.spark.sql.DataFrame
import ru.egoshin.workflow.{Application, ApplicationManager}

trait DataStorageTrait {
  def read()(implicit app: Application, appManager: ApplicationManager): DataFrame
  def write(df: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit
}
