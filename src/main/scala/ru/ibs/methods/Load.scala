package ru.ibs.methods

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.workflow.Application

protected trait Load {
  protected def load(transformedData: DataFrame)(implicit
      app: Application,
      appManager: ApplicationManager): Unit = {
    appManager.target.write(transformedData)
  }
}
