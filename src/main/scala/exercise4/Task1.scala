package exercise4

import zio.{ExitCode, Has, ULayer, URIO, URLayer}

object Task1 extends zio.App {

  trait Config
  trait Logging
  trait Parsing
  trait Database
  trait Serialization
  trait UserService

  val configLive: ULayer[Has[Config]] = ???
  val userServiceLive: URLayer[Has[Database] with Has[Logging] with Has[Serialization], Has[UserService]] = ???
  val parsingLive: ULayer[Has[Parsing]] = ???
  val serializationLive: URLayer[Has[Parsing], Has[Serialization]] = ???
  val databaseLive: URLayer[Has[Config], Has[Database]] = ???
  val loggingLive: ULayer[Has[Logging]] = ???

  type MyEnv = Has[Database] with Has[Logging] with Has[Serialization] with Has[UserService] with Has[Parsing] with Has[Config]

  def f(): URIO[MyEnv, Unit] = ???

  /**
   * Compose all dependencies by ++ operator
   * if an upstream dependency is used by many other services, it can be convenient to "pass through" that dependency,
   * and include it in the output of a layer.
   * This can be done with the >+> operator, which provides the output of one layer to another layer,
   * returning a new layer that outputs the services of both layers.
   */
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = f().provideCustomLayer(
    configLive >+> databaseLive
      ++ parsingLive >+> serializationLive
      ++ loggingLive
      >+> userServiceLive
  ).exitCode
}
