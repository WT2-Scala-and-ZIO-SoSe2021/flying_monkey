package exercise4.task3

import zio.UIO

trait JobBoard {
  /**
  Submits a job to the job board, which can later be taken up by a robot using the take method.
   */
  def submit(job: PendingJob): UIO[Unit]

  /**
  Take a job from the job board
   */
  def take(): UIO[PendingJob]
}

object JobBoard {

}
