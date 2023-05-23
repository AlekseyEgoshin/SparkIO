package ru.ibs.methods

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.workflow.Application

trait Transform {
  def transform(
      df: DataFrame)(implicit app: Application, appManager: ApplicationManager): DataFrame = df
}
