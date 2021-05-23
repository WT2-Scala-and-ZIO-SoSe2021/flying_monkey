package exercise2

/**
 * Ior[E, A] represents the inclusive or relationship, can contain E, A or both.
 * map and flatMap will work on the right side (A).
 * Only accept Throwable as E.
 *
 * @tparam A
 */
sealed trait Ior[A] {
  /**
   * Ignore left scenario
   *
   * @param f a function that take type A and return Ior[B]
   * @return Ior[B]
   */
  def flatMap[B](f: A => Ior[B]): Ior[B] = this match {
    case Left(exception) => Ior.left(exception)
    case Right(elem) => f(elem)

    // case Both(exception, elem) => ignore left scenario exception, only touch elem
    // => change elem into Ior type
    case Both(exception, elem) => {
      f(elem) match {
        // elem => exception2 => return (exception, exception2) - Not Ior => return Left(exception2)
        case Left(exception2) => Left(exception2)
        // elem => elem2 => return (exception, elem2)
        case Right(elem2) => Both(exception, elem2)
        // elem => (exception3, elem3) => return (exception, exception3, elem3) - Not Ior => return Both(exception3, elem3)
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
  def map[B](f: A => B): Ior[B] = flatMap(elem => Ior.unit(f(elem)))
}

case class Left[A](exception: Throwable) extends Ior[A]
case class Right[A](elem: A) extends Ior[A]
case class Both[A](exception: Throwable, elem: A) extends Ior[A]

/**
 * Companion object
 */
object Ior {
  def left[A](exception: Throwable): Left[A] = Left(exception)
  def right[A](elem: A): Right[A] = Right(elem)
  def both[A](exception: Throwable, elem: A): Both[A] = Both(exception, elem)

  // only case class Right take A
  def unit[A](elem: A): Ior[A] = Right(elem)
}

