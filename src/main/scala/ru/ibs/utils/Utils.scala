package ru.ibs.utils

import ru.ibs.workflow.Application

import scala.collection.mutable

trait Utils extends Constants {
  def ArgsParser(argumentList: Array[String])(app: Application): mutable.Map[String, String] = {
    val outputMap: mutable.Map[String, String] = mutable.Map[String, String]()

    argumentList.foreach(each_argument => {
      val (parameterName: String, parameterValue: String) = each_argument.split("==", 2) match {
        case Array(x, y) => (x.toLowerCase, y)
      }

//      app.logger.info(s"Parameter '$parameterName' has been received")

      outputMap.put(parameterName, parameterValue)
    })

    outputMap
  }
}