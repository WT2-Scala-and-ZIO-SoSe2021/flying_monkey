package exercise4.task3

import zio.clock.Clock
import zio.console.Console
import zio.{Has, ZIO}

/**
 * Take jobs from the JobBoard
 * Execute job, each job takes duration to execute
 * When completed, publish them on the CompletedJobsHub
 */
class RobotWorker(val name: String) extends Robot {
  override def work(): ZIO[Has[JobBoard] with Has[CompletedJobsHub] with Clock with Console, Nothing, Unit] = (
      for {
        job <- JobBoard.take()
        _ <- ZIO.sleep(job.duration)
        _ <- CompletedJobsHub.publish(CompletedJob(job.name, this))
      } yield()
    ).forever
}
