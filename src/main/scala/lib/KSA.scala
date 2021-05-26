package lib
import exercise2.{Queue, QueueLike, StackEmpty}

import scala.annotation.tailrec
import scala.util.Random

object KSA {
  val ENERGY_DECAY_FACTOR = 0.996
  val r = new Random()

  /**
   * Returns a queue containing a total of frequency elements of random values between .5 and -.5 multiplied by volume.
   * Frequency must be greater than zero and volume is between 0 and 1.
   */
  def whiteNoise(frequency: Int = 440, volume: Double = 1.0): QueueLike[Double] = {
    if(frequency <= 0 || volume < 0 || volume > 1) {
      throw new Exception("wrong input")
    }

    // Keep adding queue until it's enough
    @tailrec
    def addQueue(queue: QueueLike[Double], number: Int): QueueLike[Double] = {
      if(number == 0) return queue

      val newQueue = queue.enqueue(Random.between(-0.5, 0.5) * volume)
      addQueue(newQueue, number - 1)
    }

    addQueue(new Queue[Double](StackEmpty(), StackEmpty()), frequency)
  }

  /**
   * Takes a queue and returns a new queue.
   * Remove the front double in the queue and average it with the next double multiplied by newDouble.
   * Add newDouble to the queue.
   */
  def update(queue: QueueLike[Double]): QueueLike[Double] = {
    val newQueue = queue.dequeue().get
    val newDouble = (queue.front().get + newQueue.front().get) / 2.0 * ENERGY_DECAY_FACTOR
    newQueue.enqueue(newDouble)
  }

  @tailrec
  /**
   * Takes a queue and a function f:Double => Unit, which can be used to play audio.
   * Takes the queue, passes it to update, plays the front element of the queue and calls itself indefinitely.
   */
  def loop(queue: QueueLike[Double])(f: Double => Unit): Unit = {
    val newQueue = update(queue)
    if(Math.abs(newQueue.front().get) < 0.0000000001)
      return
    f(newQueue.front().get)
    loop(newQueue)(f)
  }
}
