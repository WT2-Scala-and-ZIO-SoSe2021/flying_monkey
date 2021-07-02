package exercise4.task3

import zio.{Has, ZIO}

/***
 * When job is completed => post a nice comment about the robot who completed it on the news
 * Including only the name of the robot, who completed it
 */
class RobotPraiser(val name: String) extends Robot {
  override def work(): ZIO[Has[CompletedJobsHub] with Has[News], Any, Unit] = CompletedJobsHub.subscribe.use {
    queue => (
        for {
          job <- queue.take
          _ <- News.post(s"Some nice comment about ${job.completedBy.name}, that guy probably did really good in Scala")
        } yield ()
      ).forever
  }
}
