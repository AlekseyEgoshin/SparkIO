package ru.egoshin.workflow

import org.apache.hadoop.fs.FileSystem
import org.apache.log4j.Logger
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

/**
 * В классе определяются стандартные сущности, которые используются внутри кода
 */
class Application {

  def sparkSession: SparkSession.Builder = SparkSession
    .builder()
    .master("local[1]")
    .appName("Local Test")
    .config("spark.ui.enabled", "false")
    .config("spark.sql.shuffle.partitions", 1)

//  lazy val spark: SparkSession = SparkSession.builder().enableHiveSupport().getOrCreate()
  lazy val spark: SparkSession = sparkSession.getOrCreate()
  lazy val sc: SparkContext = spark.sparkContext
  lazy val fs: FileSystem = FileSystem.get(sc.hadoopConfiguration)
  val logger: Logger = Logger.getLogger("Demo")
}
