package exercise4.task3

import zio.clock.Clock
import zio.console.{Console, putStrLn}
import zio.{Has, ZIO}

/***
 * Whenever there are news, it proclaims them (outputs the string to the console)
 * For simplicity, it can just run synchronously in the main fiber (no need to fork it)
 */
class RobotReporter(val name: String) extends Robot {
  override def work(): ZIO[Has[News] with Clock with Console, Any, Unit] =
    (
      for {
        news <- News.proclaim()
        _ <- putStrLn(news)
      } yield ()
    ).forever
}
