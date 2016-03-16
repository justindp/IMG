

name := "IMG"

version := "1.0"

scalaVersion := "2.11.8"

parallelExecution in Test := false

//libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
//libraryDependencies += "junit" % "junit" % "4.12" % "test"


lazy val img = project.in(file("."))
  .aggregate(
    imgTest
  )
  .settings(
    scalaVersion := "2.11.8",
    aggregate in update := false
  )

lazy val imgTest = project
  .in(file("imgTest"))
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest" % "scalatest_2.10" % "2.0" % "test",
      "junit" % "junit" % "4.12" % "test"
    ))
  .settings(
    name := "agct-web-tuts-import",
    //    jarName in assembly := "agct-web-tuts-import.jar",
    //    outputPath in assembly := file("artifacts/agct-web-tuts-import.jar"),
    //    mainClass in assembly := Some("main.main.Program"),
    packageOptions in(Compile, packageBin) += Package.ManifestAttributes(java.util.jar.Attributes.Name.SEALED -> "true")
    //    assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
  )
