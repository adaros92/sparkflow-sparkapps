package common

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}


trait SparkWrapper {

  // Set config
  protected val sparkConf: SparkConf = new SparkConf()
  protected def config(key: String, value: String): Unit = {
    this.sparkConf.set(key, value)
  }
  def conf: SparkConf = this.sparkConf

  def appName: String

  // Build the spark session and retrieve spark context
  protected def builder: SparkSession.Builder = {
    SparkSession
      .builder()
      .appName(appName)
      .config(this.conf)
  }
  def spark: SparkSession = builder.getOrCreate()
  def sc: SparkContext = spark.sparkContext

}

trait SparkLocalWrapper extends SparkWrapper{
  override def builder: SparkSession.Builder = {
    super.builder.master("local")
  }
}