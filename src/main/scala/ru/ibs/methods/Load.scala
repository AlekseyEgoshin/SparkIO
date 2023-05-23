package ru.ibs.methods

import org.apache.spark.sql.{DataFrame, SaveMode}
import ru.ibs.meta.ApplicationManager
import ru.ibs.workflow.Application

trait Load {
  val saveMode = SaveMode.Overwrite

  def load(transformedData: DataFrame)(implicit
      app: Application,
      appManager: ApplicationManager): Unit = {
    appManager.target.write(transformedData)
  }
}
