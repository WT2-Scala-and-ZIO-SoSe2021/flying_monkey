package exercise2
sealed trait Ior[A] {

  def flatMap[B](f: A => Ior[B]): Ior[B] = this match {
    case Left(exception) => Ior.left(exception)
    case Right(elem) => f(elem)
    case Both(exception, elem) =>
      f(elem) match {
        case Left(exception2) => Left(exception2)
        case Right(elem2) => Both(exception, elem2)
        case Both(exception3, elem3) => Both(exception3, elem3)
      }
  }
  def map[B](f: A => B): Ior[B] = flatMap(elem => Ior.unit(f(elem)))
}

object Ior {
  def left[A](throwable: Throwable): Left[A] = Left(throwable)
  def right[A](elem: A): Right[A] = Right(elem)
  def both[A](throwable: Throwable, elem: A): Both[A] = Both(throwable, elem)
  def unit[A](elem: A): Ior[A] = Right(elem)
}

case class Left[A](throwable: Throwable) extends Ior[A]
case class Right[A](elem: A) extends Ior[A]
case class Both[A](throwable: Throwable, elem: A) extends Ior[A]
