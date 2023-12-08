package ru.egoshin.methods

import org.apache.spark.sql.DataFrame
import ru.egoshin.workflow.{Application, ApplicationManager}

trait Extract {
  protected def extract(implicit app: Application, appManager: ApplicationManager): DataFrame = {
    appManager.source.read
  }
}
