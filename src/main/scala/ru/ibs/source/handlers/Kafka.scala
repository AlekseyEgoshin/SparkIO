package ru.ibs.source.handlers

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.source.DataStorageTrait
import ru.ibs.workflow.Application

/**
 *
 * TODO: Добавить считывание и запись
 * Для чтения используем Consumer
 * Для записи используем Producer
 */
class Kafka extends DataStorageTrait{
  override def read()(implicit app: Application, appManager: ApplicationManager): DataFrame =
    app.spark.readStream.format("kafka").load()

  override def write(df: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit = ???
}
