package exercise4.task3

import zio.{Has, ZIO}

/***
 * When a job is completed => post on the News
 * including the name of the job and the robot who completed it
 */
class RobotOverseer(val name: String) extends Robot {
  override def work(): ZIO[Has[CompletedJobsHub] with Has[News], Nothing, Unit] =
    CompletedJobsHub.subscribe.use {
      queue => (
            for {
              job <- queue.take
              _ <- News.post(s"${job.completedBy.name} had completed ${job.name}")
            } yield ()
        ).forever
    }
}
