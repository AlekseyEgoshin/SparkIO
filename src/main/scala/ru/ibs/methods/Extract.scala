package ru.ibs.methods

import org.apache.spark.sql.DataFrame
import ru.ibs.workflow.{Application, ApplicationManager}

trait Extract {
  protected def extract(implicit app: Application, appManager: ApplicationManager): DataFrame = {
    appManager.source.read
  }
}
