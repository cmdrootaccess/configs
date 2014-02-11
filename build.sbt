name := "root"

lazy val core = project
lazy val support = project dependsOn core

version in ThisBuild := "0.2.1"

organization in ThisBuild := "com.github.kxbmap"

scalaVersion in ThisBuild := "2.10.3"

scalacOptions in ThisBuild ++= Seq(
  "-feature",
  Opts.compile.unchecked,
  Opts.compile.deprecation
)

Publish.settings

publish := {}

publishLocal := {}

publishArtifact := false

sonatypeSettings
