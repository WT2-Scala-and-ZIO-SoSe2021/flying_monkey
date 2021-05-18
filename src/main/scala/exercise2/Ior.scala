package exercise2

/**
 * Represents the inclusive or relationship
 * Can contain E, A or both
 * map and flatMap will work on the right side (A)
 * only accept Throwable as E
 * @tparam A
 */
sealed trait Ior[A]

case class Left[A](elem: Throwable) extends Ior[A]
case class Right[A](elem: A) extends Ior[A]
case class Both[A](left: Throwable, elem: A) extends Ior[A]

/**
 * Companion object
 */
object Ior {
  def left[A](elem: Throwable): Left[A] = Left(elem)
  def right[A](elem: A): Right[A] = Right(elem)
  def both[A](left: Throwable, elem: A): Both[A] = Both(left, elem)

  def unit[A](elem: A): Ior[A] = Right(elem)
}

