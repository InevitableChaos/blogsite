ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "2.13.13"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

lazy val `akka_http`: Seq[ModuleID] = {
  val AkkaVersion = "2.9.2"
  val AkkaHttpVersion = "10.6.1"


  Seq(
    "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
    "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
    "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
  )
}


libraryDependencies ++= `akka_http`

lazy val root = (project in file("."))
  .settings(
    name := "blogsite"
  )
