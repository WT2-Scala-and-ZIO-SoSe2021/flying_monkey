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
    case Left2(exception) => Ior2.left(exception)
    case Right2(elem) => f(elem)

    // case Both2(exception, elem) => ignore left scenario exception, only touch elem
    // => change elem into Ior type
    case Both2(exception, elem) => {
      f(elem) match {
        // elem => exception2 => return (exception, exception2) - Not Ior => return Left2(exception2)
        case Left2(exception2) => Left2(exception2)
        // elem => elem2 => return (exception, elem2)
        case Right2(elem2) => Both2(exception, elem2)
        // elem => (exception3, elem3) => return (exception, (exception3, elem3)) => return Both2(exception3, elem3)
        case Both2(exception3, elem3) => Both2(exception3, elem3)
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

case class Left2[E <: Throwable, A](exception: Throwable) extends Ior2[E, A]
case class Right2[E <: Throwable, A](elem: A) extends Ior2[E, A]
case class Both2[E <: Throwable, A](exception: Throwable, elem: A) extends Ior2[E, A]

/**
 * Companion object
 */
object Ior2 {
  def left[E <: Throwable, A](exception: Throwable): Left2[E, A] = Left2(exception)
  def right[E <: Throwable, A](elem: A): Right2[E, A] = Right2(elem)
  def both[E <: Throwable, A](exception: Throwable, elem: A): Both2[E, A] = Both2(exception, elem)

  // only case class Right2 take A
  def unit[E <: Throwable, A](elem: A): Ior2[E, A] = Right2(elem)
}

