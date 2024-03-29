package ru.egoshin.utils

import org.apache.spark.sql.SparkSession

trait Spark {
  implicit val spark: SparkSession = SparkSession
    .builder()
    .enableHiveSupport()
    .getOrCreate()

}
