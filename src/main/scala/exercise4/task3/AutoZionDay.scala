package exercise4.task3

import zio.duration.Duration
import zio.{ExitCode, URIO, ZIO}

import java.util.concurrent.TimeUnit

object AutoZionDay extends zio.App {
  val elder1 = new RobotElder("Elder 1")
  val elder2 = new RobotElder("Elder 2")
  val overseer = new RobotOverseer("Overseer")
  val praiser = new RobotPraiser("Praiser")
  val reporter = new RobotReporter("Reporter")
  val worker1 = new RobotWorker("Worker 1")
  val worker2 = new RobotWorker("Worker 2")

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = (
      for {
        _ <- overseer.work().fork
        _ <- worker1.work().fork
        _ <- worker2.work().fork
        _ <- ZIO.sleep(Duration.apply(1, TimeUnit.SECONDS))
        _ <- elder1.work().fork
        _ <- elder2.work().fork
        _ <- praiser.work().fork
        _ <- reporter.work()
      } yield ()
    )
    .provideCustomLayer(
      JobBoardLive.layer ++ CompletedJobsHubLive.layer ++ NewsLive.layer
    )
    .exitCode


}
