package exercise3

import exercise2._

import lib.KSA.ENERGY_DECAY_FACTOR
import lib.StdAudio

import zio.random._
import zio.{UIO, URIO, ZIO}

import scala.util.{Failure, Success}

object ZioApp extends App {
  def run(args: List[String]) =
    myAppLogic.exitCode

  val myAppLogic =
    for {
      noise <- whiteNoise()
      _ <- loop(noise)
    } yield ()

  def play(sound: Double): UIO[Unit] = ZIO.effectTotal(StdAudio.play(sound))

  /**
   * Returns a queue containing a total of frequency elements of random values between .5 and -.5 multiplied by volume.
   * Frequency must be greater than zero and volume is between 0 and 1.
   */
  def whiteNoise(frequency: Int = 440, volume: Double = 1.0): URIO[Random, Queue[Double]] = {
    if(frequency <= 0 || volume < 0 || volume > 1) {
      ZIO.fail("wrong input")
    }

    ZIO.foldLeft(0 to frequency)(new Queue[Double](StackEmpty(), StackEmpty())) {
      (q, _) =>
        for {
          randomValue <- nextDoubleBetween(-0.5, 0.5)
          queue <- ZIO.effectTotal(q.enqueue(randomValue * volume))
        } yield queue
    }
  }

  /**
   * Takes a queue and returns a new queue.
   * Remove the front double in the queue and average it with the next double multiplied by newDouble.
   * Add newDouble to the queue.
   */
  def update(queue: QueueLike[Double]): Option[QueueLike[Double]] = {
    val newQueue = queue.dequeue()
    newQueue match {
      case Success(newQueue) => Some(
        newQueue.enqueue((queue.front().get + newQueue.front().get) / 2.0 * ENERGY_DECAY_FACTOR)
      )
      case Failure(_) => None
    }
  }

  /**
   * Takes a queue and a function f:Double => Unit, which can be used to play audio.
   * Takes the queue, passes it to update, plays the front element of the queue and calls itself indefinitely.
   */
  def loop(queue: QueueLike[Double]): ZIO[Random, Throwable, Unit] = {
    val newQueue = update(queue).get
    for {
      _ <- play(newQueue.front.get)
      _ <- loop(newQueue)
    } yield ()
  }
}
