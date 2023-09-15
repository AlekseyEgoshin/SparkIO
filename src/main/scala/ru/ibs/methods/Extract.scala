package ru.ibs.methods

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.workflow.Application

protected trait Extract {
  protected def extract(implicit app: Application, appManager: ApplicationManager): DataFrame = {
    appManager.source.read
  }
}
