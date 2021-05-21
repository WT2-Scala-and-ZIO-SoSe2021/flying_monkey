package exercise2

import org.scalatest.flatspec.AnyFlatSpec

class IorSpec extends AnyFlatSpec {
  val exception = new RuntimeException("a grave error")

  val a = Ior.right(2)
  val b = a.map(x => x * 4)
  val c = b.flatMap(_ => Ior.right("a string"))
  val d = c.flatMap(_ => Ior.left[String](new RuntimeException("a grave error")))
  val e = d.map(x => x + "something")

  val both = Ior.both(new RuntimeException("not fatal"), 21)
  val both1 = both.map(x => x * 2)
  val both2 = both.flatMap(_ => Ior.left[Int](new RuntimeException("fatal error")))
  val both3 = both.flatMap(_ => Ior.right(480))
  val both4 = both.flatMap(x => Ior.both(new RuntimeException("another not fatal"), x * 3))

  /**
   * Test case of task 1A
   */
  "right, left, both" should "return the respective Ior" in {
    assert(Ior.right(23) === Right(23))
    assert(Ior.right("string test") === Right("string test"))
    assert(Ior.right(true) === Right(true))
    assert(Ior.left[String](exception) === Left(exception))
    assert(Ior.both(exception, 23) === Both(exception, 23))
  }

  /**
   * Test case of task 1B
   */
  "unit" should "return Ior[A]" in {
    assert(Ior.unit(23) === Right(23))
  }

  /**
   * Test case of task 1C + 1D
   */
  "a" should "return Right(2)" in {
    assert(a === Right(2))
  }
  "b" should "return Right(8)" in {
    assert(b === Right(8))
  }
  "c" should "return Right(\"a string\")" in {
    assert(c === Right("a string"))
  }
  "d" should "return Left(java.lang.RuntimeException: a grave error)" in {
    assert(d === Ior.left[String](new RuntimeException("a grave error")))
  }
  "e" should "return Left(java.lang.RuntimeException: a grave error)" in {
    assert(e === Left(new RuntimeException("a grave error")))
  }

  "both1" should "return Both(java.lang.RuntimeException: not fatal,42)" in {
    assert(both1 === Both(new RuntimeException("not fatal"), 42))
  }
  "both2" should "return Left(java.lang.RuntimeException: fatal error)" in {
    assert(both2 === Left(new RuntimeException("fatal error")))
  }
  "both3" should "return Both(java.lang.RuntimeException: not fatal,480)" in {
    assert(both3 === Both(new RuntimeException("not fatal"), 480))
  }
  "both4" should "return Both(java.lang.RuntimeException: another not fatal,63)" in {
    assert(both4 === Both(new RuntimeException("another not fatal"), 63))
  }
}