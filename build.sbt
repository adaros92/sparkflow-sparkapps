

scalaVersion := "2.12.1"

name := "sparkflow-sparkapps"
organization := "ch.epfl.scala"
version := "1.0"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.0.1",
  "org.apache.spark" %% "spark-sql" % "3.0.1"
)
libraryDependencies += "com.github.scopt" % "scopt_native0.2_2.11" % "3.6.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.5" % "test"

assemblyMergeStrategy in assembly := {
  case "reference.conf" => MergeStrategy.concat
  case "application.conf" => MergeStrategy.concat
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

