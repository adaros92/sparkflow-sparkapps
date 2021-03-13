package sampleapps

import common.SparkWrapper
import org.apache.spark.rdd.RDD

object SparkWordCount extends SparkWrapper {

  override def appName = "Spark Word Count"

  case class CliArgs(textToCount: String = "")

  def parseCli(args: Seq[String]): CliArgs = {
    val parser = new scopt.OptionParser[CliArgs]("SparkWordCountApp") {
      head("Spark word count app")
      opt[String]("textToCount")
        .required()
        .text("The text to count words with")
        .action((param, args) => args.copy(textToCount = param))
    }
    parser.parse(args, CliArgs()).getOrElse({
      parser.showUsage
      throw new Exception("could not parse command")
    })
  }

  /**
   * Calculates the count of unique words in a collection of strings
   * @param text a sequence of individual strings to count words from
   * @return an RDD of word to count tuples
   */
  def countWords(text: Seq[String]): RDD[(String, Int)] = {
    val lines = spark.sparkContext.parallelize(text)
    lines.flatMap(line => line.split(" ")).map(word => (word, 1))
      .reduceByKey(_ + _)
  }

  def main(args: Array[String]): Unit = {
    val cliArgs = parseCli(args)
    val textToCount = cliArgs.textToCount.split(",").toSeq

    val counts = countWords(textToCount)
    counts.foreach(println)
  }
}
