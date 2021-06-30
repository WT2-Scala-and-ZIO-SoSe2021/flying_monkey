package exercise4

import zio.{ExitCode, URIO, ZIO}

object Task2 extends zio.App {
  /**
   * Start a fiber: [fork] an effect to give a fiber
   * [join]: waiting for fiber to compute value
   * get rid of fiber by [interrupt]
   * [zipPar] to execute actions in parallel
   *
   * The first computation is an interruption.
   */
  val app = for {
    fiber <- (ZIO.interrupt zipPar ZIO.fail("Error")).fork
    _ <- fiber.join
  } yield ()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = app.exitCode
}
