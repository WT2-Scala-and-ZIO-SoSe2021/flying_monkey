package exercise2

import scala.util.Try

// FILO
trait QueueLike[T] {
  val inQueue: StackLike[T]
  val outQueue: StackLike[T]

  def enqueue(elem: T): QueueLike[T]

  def dequeue(): Try[QueueLike[T]]

  def front(): Option[T]

  def isEmpty: Boolean
}

case class StackQueue[T](inQueue: StackLike[T], outQueue: StackLike[T]) extends QueueLike[T] {
  override def enqueue(elem: T): StackQueue[T] = {
    StackQueue(this.inQueue.push(elem), this.outQueue)
  }

  override def dequeue(): Try[QueueLike[T]] = this.outQueue match {
    case StackCons(_, _) => {
      val out = this.outQueue.pop().get
      Try(StackQueue(this.inQueue, out))
    }
    case StackEmpty() => this.inQueue match {
      case StackCons(_, _) => {
        val out = this.inQueue.reverse().pop().get
        val in = StackEmpty[T]()
        Try(StackQueue(in, out))
      }
      case StackEmpty() => Try(StackQueue(StackEmpty(), StackEmpty()))
    }
  }

  override def front(): Option[T] = this.outQueue match {
    case StackEmpty() => this.inQueue match {
      case StackEmpty() => None
      case _ => this.inQueue.reverse().top()
    }
    case _ => this.outQueue.top()
  }

  override def isEmpty: Boolean = inQueue.isEmpty && outQueue.isEmpty
}

