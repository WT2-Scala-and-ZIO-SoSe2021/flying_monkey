package exercise2

/**
 * Ior[E, A] represents the inclusive or relationship, can contain E, A or both.
 * map and flatMap will work on the right side (A).
 * Only accept Throwable as E.
 *
 * @tparam A
 */
sealed trait Ior2[E <: Throwable, A] {
  /**
   * Ignore left scenario
   *
   * @param f a function that take type A and return Ior[B]
   * @return Ior[B]
   */
  def flatMap[E <: Throwable, B](f: A => Ior2[E, B]): Ior2[E, B] = this match {
    case Left(exception) => Ior2.left(exception)
    case Right(elem) => f(elem)

    // case Both(exception, elem) => ignore left scenario exception, only touch elem
    // => change elem into Ior type
    case Both(exception, elem) => {
      f(elem) match {
        // elem => exception2 => return (exception, exception2) - Not Ior => return Left(exception2)
        case Left(exception2) => Left(exception2)
        // elem => elem2 => return (exception, elem2)
        case Right(elem2) => Both(exception, elem2)
        // elem => (exception3, elem3) => return (exception, (exception3, elem3)) => return Both(exception3, elem3)
        case Both(exception3, elem3) => Both(exception3, elem3)
      }
    }
  }

  /**
   * Map the scenarios
   *
   * @param f function
   * @return Ior[B]
   */
  def map[E <: Throwable, B](f: A => B): Ior2[E, B] = flatMap(elem => Ior2.unit(f(elem)))
}

case class Left[E <: Throwable, A](exception: Throwable) extends Ior2[E, A]
case class Right[E <: Throwable, A](elem: A) extends Ior2[E, A]
case class Both[E <: Throwable, A](exception: Throwable, elem: A) extends Ior2[E, A]

/**
 * Companion object
 */
object Ior2 {
  def left[E <: Throwable, A](exception: Throwable): Left[E, A] = Left(exception)
  def right[E <: Throwable, A](elem: A): Right[E, A] = Right(elem)
  def both[E <: Throwable, A](exception: Throwable, elem: A): Both[E, A] = Both(exception, elem)

  // only case class Right take A
  def unit[E <: Throwable, A](elem: A): Ior2[E, A] = Right(elem)
}

