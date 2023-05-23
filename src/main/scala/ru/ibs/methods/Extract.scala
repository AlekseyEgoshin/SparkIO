package ru.ibs.methods

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.workflow.Application

trait Extract {
  def extract(implicit app: Application, appManager: ApplicationManager): DataFrame = {
    appManager.source.read
  }
}
