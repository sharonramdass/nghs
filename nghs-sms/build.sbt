name := """nghs-sms"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "6.0.5",
  "commons-io" % "commons-io" % "2.4"
)
