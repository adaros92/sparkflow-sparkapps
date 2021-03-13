package testutils

import common.SparkLocalWrapper

import org.apache.log4j.Level
import org.apache.spark.SparkConf

object SparkTestWrapper extends SparkLocalWrapper {
  {
    org.apache.log4j.Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    org.apache.log4j.Logger.getLogger("org.apache.hadoop.input.LineRecordReader").setLevel(Level.ERROR)
    org.apache.log4j.Logger.getLogger(
      "org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter").setLevel(Level.ERROR)
    org.apache.log4j.Logger.getLogger("org.apache.hadoop.output.FileOutputCommitter").setLevel(Level.ERROR)
    org.apache.log4j.Logger.getLogger(
      "org.apache.hadoop.mapreduce.lib.input.LineRecordReader").setLevel(Level.ERROR)
  }

  override def appName: String = "SparkTestWrapper"

  override def conf: SparkConf = {
    config("spark.sql.shuffle.partitions", "1")
    config("spark.ui.enabled", "false")
    config("spark.driver.bindAddress", "127.0.0.1")
    config("spark.driver.host", "localhost")
    config("spark.sql.catalogImplementation", "in-memory")
    config("spark.driver.port", "8888")
    config("spark.sql.autoBroadcastJoinThreshold", "-1")
    super.conf
  }
}