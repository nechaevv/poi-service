name := "poi-service"

organization := "com.github.nechaevv"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

val slickPgVersion = "0.12.0"

val akkaVersion = "2.4.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
  "com.typesafe.play" %% "play-json" % "2.5.1",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "org.postgresql" % "postgresql" % "9.4.1207",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.1",
  "com.zaxxer" % "HikariCP" % "2.4.1",
  "com.github.tminglei" %% "slick-pg" % slickPgVersion,
  "com.github.tminglei" %% "slick-pg_date2" % slickPgVersion
)
