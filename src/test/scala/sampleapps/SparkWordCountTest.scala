package sampleapps

import org.scalatest.funsuite.AnyFunSuite
import org.apache.spark.sql.SparkSession

import testutils.SparkTestWrapper

class SparkWordCountTest extends AnyFunSuite {

  implicit val spark: SparkSession = SparkTestWrapper.spark

  test("testing that countWords can correctly generate a count of words from a block of text"){
    val wordsToCount = Seq("some words to count", "some other words to count")
    val countsOne = SparkWordCount.countWords(wordsToCount)
    val expectedCount = Set(("some",2), ("words",2), ("count",2), ("other",1), ("to",2))
    assert(countsOne.collect().toSet == expectedCount)
  }

}