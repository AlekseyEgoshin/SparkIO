package ru.ibs.source.handlers

import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.{DataFrame, Dataset}
import ru.ibs.meta.{ApplicationManager, KafkaMessage}
import ru.ibs.source.DataStorageTrait
import ru.ibs.workflow.Application

import scala.language.postfixOps

/**
 *
 * TODO: Добавить считывание и запись
 * Для чтения используем Consumer
 * Для записи используем Producer
 */
case class Kafka(
              servers: String,
              topic: String,
              protocol: Option[String],
              offset: Option[String]
           ) extends DataStorageTrait {
  private def getConfigForStreaming: Map[String, String] = {
    Map[String, String](
      "kafka.bootstrap.servers" -> servers,
      "bootstrap.servers" -> servers,
      "subscribe" -> topic,
      "includeHeaders" -> "true",
      "group.id" -> "spark-kafka-source",
      if (offset.isDefined)
        "startingOffsets" -> offset.get
      else
        "startingOffsets" -> "earliest"
    )
  }

  override def read()(implicit app: Application, appManager: ApplicationManager): DataFrame = {
    import app.spark.implicits._

    var dataset: Dataset[KafkaMessage] = app.spark.emptyDataset

    // Почитать про тригеры
    app.spark.readStream
      .format("kafka")
      .options(getConfigForStreaming)
      .load()
      .as[KafkaMessage]
      .writeStream
      .trigger(Trigger.Once)
      .foreachBatch { (batchData: Dataset[KafkaMessage], _) =>
        if (!batchData.isEmpty) dataset = dataset.union(batchData)
      }
      .start()
      .awaitTermination()

    dataset.toDF()
  }

  override def write(df: DataFrame)(implicit app: Application, appManager: ApplicationManager): Unit =
    df
      .writeStream
      .format("kafka")
      .options(getConfigForStreaming)
}
