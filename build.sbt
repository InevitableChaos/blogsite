import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._
import sbtrelease.Version.Bump.Bugfix

name := "blogsite"
organization := "Inevitable Chaos"

val scalaV = "2.13.3"
val scalacOpts = Seq(
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:params",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates",
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard"
)

scalaVersion := scalaV
scalacOptions ++= scalacOpts
addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

resolvers ++= {
  Seq(
    "jitpack" at "https://jitpack.io",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots"),
  )
}

val mongo = {
  val version = "4.3.0"

  Seq(
    "org.mongodb.scala" %% "mongo-scala-driver" % version
  )
}

libraryDependencies ++= mongo
githubTokenSource := TokenSource.GitConfig("github.token") || TokenSource.Environment("GITHUB_TOKEN")

assemblyOutputPath in assembly := file("application.jar")
assemblyMergeStrategy in assembly := {
  case "META-INF/io.netty.versions.properties" => MergeStrategy.first
  case "module-info.class"                     => MergeStrategy.first
  case PathList("javax", "servlet", _ @ _*)    => MergeStrategy.first
  case PathList("javax", "annotation", _ @ _*) => MergeStrategy.first
  case x                                       => (assemblyMergeStrategy in assembly).value(x)
}

releaseIgnoreUntrackedFiles := true
releaseVersionBump := Bugfix
releaseProcess := Seq[ReleaseStep](
  inquireVersions,
  runClean,
  releaseStepCommand("assembly"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion,
  pushChanges
)
