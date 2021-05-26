package exercise2

import scala.util.Try

// FIFO
sealed trait StackLike[T] {
  def push(elem: T): StackLike[T]

  // Try because StackLike can be empty => return exception
  def pop(): Try[StackLike[T]]

  def top(): Option[T]

  def isEmpty: Boolean

  def reverse(): StackLike[T]
}

case class StackEmpty[T]() extends StackLike[T] {
  override def push(elem: T): StackLike[T] = StackCons(elem, this)

  override def top(): Option[T] = None

  override def isEmpty: Boolean = true

  override def reverse(): StackLike[T] = this
}

case class StackCons[T](head: T, tail: StackLike[T]) extends StackLike[T] {
  override def push(elem: T): StackLike[T] = StackCons(elem, this)

  override def top(): Option[T] = Option(head)

  override def isEmpty: Boolean = false

  override def reverse(): StackLike[T] = {
    val beginStack = StackCons(head, StackEmpty())
    this.Reverse(tail, beginStack)
  }

  private def Reverse(tail: StackLike[T], all: StackCons[T]): StackLike[T] = tail match {
    case StackCons(smallHead, smallTail) => {
      val newAll = StackCons(smallHead, all)
      this.Reverse(smallTail, newAll)
    }
    case StackEmpty() => all
  }
}


