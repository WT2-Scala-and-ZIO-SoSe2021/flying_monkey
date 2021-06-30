package exercise4

import zio.ZIO

package object task3 {
  type MyEnv

  trait Robot {
    val name: String
    def work(): ZIO[MyEnv, Any, Unit]
  }

  sealed trait Job {
    val name: String
  }

  case class PendingJob(name: String, duration: zio.duration.Duration) extends Job
  case class CompletedJob(name: String, completedBy: Robot) extends Job
}
