package ru.ibs.methods

import org.apache.spark.sql.DataFrame
import ru.ibs.workflow.{Application, ApplicationManager}

trait Transform {
  protected def transform(
      df: DataFrame)(implicit app: Application, appManager: ApplicationManager): DataFrame = df
}
