package ru.ibs.methods

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.Application
import ru.ibs.workflow.ApplicationManager

trait Transform {
  protected def transform(
      df: DataFrame)(implicit app: Application, appManager: ApplicationManager): DataFrame = df
}
