package ru.ibs.workflow

import org.apache.hadoop.fs.FileSystem
import org.apache.log4j.Logger
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

class Application {
  lazy val spark: SparkSession = SparkSession.builder().enableHiveSupport().getOrCreate()
  lazy val sc: SparkContext = spark.sparkContext
  lazy val fs: FileSystem = FileSystem.get(sc.hadoopConfiguration)
  val logger: Logger = Logger.getLogger("Demo")
}
