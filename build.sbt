name := "flying_monkey"

version := "0.1"

scalaVersion := "2.13.5"

val scalatest = "3.2.5"
val zioVersion = "1.0.9"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % scalatest % "test",
  "org.scalatest" %% "scalatest" % scalatest % "test",
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-test" % zioVersion % "test",
  "dev.zio" %% "zio-test-sbt" % zioVersion % "test",
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")