package ru.egoshin.methods

import org.apache.spark.sql.DataFrame
import ru.egoshin.workflow.{Application, ApplicationManager}

trait Transform {
  protected def transform(
      df: DataFrame)(implicit app: Application, appManager: ApplicationManager): DataFrame = df
}
