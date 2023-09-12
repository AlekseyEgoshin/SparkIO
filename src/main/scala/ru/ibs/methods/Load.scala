package ru.ibs.methods

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.workflow.Application

trait Load {
  def load(transformedData: DataFrame)(implicit
      app: Application,
      appManager: ApplicationManager): Unit = {
    appManager.target.write(transformedData)
  }
}
