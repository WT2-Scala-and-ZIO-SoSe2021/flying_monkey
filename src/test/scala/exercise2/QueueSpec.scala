package exercise2

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Success

class QueueSpec extends AnyFlatSpec {
  /**
   * Test case of task 2B
   */
  val emptyQueue = StackQueue(StackEmpty[Int](), StackEmpty[Int]())
  val stackQueue = StackQueue(StackCons(1, StackCons(2, StackEmpty())), StackEmpty())
  val stackQueue2 = StackQueue(StackEmpty(), StackCons(1, StackCons(2, StackEmpty())))

  "enqueue" should "add one element to the input queue" in {
    assert(emptyQueue.enqueue(1) === StackQueue(StackCons(1, StackEmpty()), StackEmpty()))
    assert(stackQueue.enqueue(1) === StackQueue(StackCons(1, StackCons(1, StackCons(2, StackEmpty()))), StackEmpty()))
    assert(stackQueue2.enqueue(1) === StackQueue(StackCons(1, StackEmpty()), StackCons(1, StackCons(2, StackEmpty()))))
  }

  "dequeue" should "take first element out of the output queue" in {
    assert(emptyQueue.dequeue() === Success(StackQueue(StackEmpty(), StackEmpty())))
    assert(stackQueue2.dequeue().get === StackQueue(StackEmpty(), StackCons(2, StackEmpty())))
  }

  "dequeue" should "reverse input queue and take first element out of the input queue if the output queue is empty" in {
    assert(stackQueue.dequeue().get === StackQueue(StackEmpty(), StackCons(1, StackEmpty())))
  }

  "front" should "return the front element" in {
    assert(emptyQueue.front() === None)
    assert(stackQueue.front().get === 2)
    assert(stackQueue2.front().get === 1)
  }

  "isEmpty" should "return boolean" in {
    assert(emptyQueue.isEmpty)
    assert(!stackQueue.isEmpty)
    assert(!stackQueue2.isEmpty)
  }
}