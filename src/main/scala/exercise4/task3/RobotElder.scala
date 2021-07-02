package exercise4.task3

import zio.clock.Clock
import zio.duration.durationInt
import zio.{Has, Schedule, ZIO}

/**
 * Publish jobs to the JobBoard at specific intervals (zio.Schedule)
 * */
class RobotElder(val name: String) extends Robot {
  override def work(): ZIO[Has[JobBoard] with Clock, Nothing, Unit] =
    JobBoard.submit(PendingJob("Some job", 1.second)) repeat Schedule.spaced(5.second).unit
}