package ru.ibs.workflow

import org.apache.spark.sql.DataFrame
import ru.ibs.meta.ApplicationManager
import ru.ibs.methods.{Extract, Load, Transform}

class Program(argumentsMap: Map[String, String])(implicit app: Application)
    extends Extract
    with Transform
    with Load {

  private implicit val appManager: ApplicationManager = new ApplicationManager(argumentsMap)(app)

  def run(): Unit = {
    // extract
    val extractDf: DataFrame = extract

    // transform
    val transformedDf: DataFrame = transform(extractDf)
    println(extractDf)

    // load
    load(transformedDf)
  }

}
