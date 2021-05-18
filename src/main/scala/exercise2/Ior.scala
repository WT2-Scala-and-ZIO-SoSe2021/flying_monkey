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

    // nested pattern matching because flatMap is right biased => only work on A
    case Both(exception, elem) => {
      f(elem) match {
        // f: elem_A => left => Left()
        case Left(exception2) => Left(exception2)
        // f: elem_A => right => Both()
        case Right(elem2) => Both(exception, elem2)
        // f: elem_A => both => Both()
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

