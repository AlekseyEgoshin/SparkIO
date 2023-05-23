package ru.ibs.source

import org.apache.spark.sql.DataFrame

trait DataStorageTrait {
  def read(): DataFrame
  def write(df: DataFrame): Unit
}
