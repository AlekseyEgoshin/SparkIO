package ru.ibs.workflow

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.Application
import ru.ibs.methods.{Extract, Load, Transform}

/**
 * В классе описывается основная логика программы. Принимает на вход два параметра:
 * @param argumentsMap
 *   \- список аргументов, переданных в spark-submit в виде пары ключ-значение
 * @param app
 *   \- класс, в котором описаны стандартные сущности, используемые во всех классах
 */
class Program(argumentsMap: Map[String, String])(implicit app: Application)
    extends Extract
    with Transform
    with Load {

  private implicit val appManager: ApplicationManager = new ApplicationManager(argumentsMap)(app)

  def run(): Unit = {
    // extract
    app.logger.info("CHECKPOINT: Start extraction process")
    val extractDf: DataFrame = extract
    app.logger.info("CHECKPOINT: Extract process completed successfully")

    // transform
    app.logger.info("CHECKPOINT: Start transformation process")
    val transformedDf: DataFrame = transform(extractDf)
    app.logger.info("CHECKPOINT: Transformation process completed successfully")

    // load
    app.logger.info("CHECKPOINT: Start uploading process")
    load(transformedDf)
    app.logger.info("CHECKPOINT: uploading process completed successfully")
  }

}
