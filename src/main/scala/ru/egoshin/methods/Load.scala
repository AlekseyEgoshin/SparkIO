package ru.egoshin.methods

import org.apache.spark.sql.DataFrame
import ru.egoshin.workflow.{Application, ApplicationManager}

trait Load {
  protected def load(transformedData: DataFrame)(implicit
      app: Application,
      appManager: ApplicationManager): Unit = {
    appManager.target.write(transformedData)
  }
}
