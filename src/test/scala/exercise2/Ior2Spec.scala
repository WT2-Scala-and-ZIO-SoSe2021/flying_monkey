package exercise2

import org.scalatest.flatspec.AnyFlatSpec

class Ior2Spec extends AnyFlatSpec {
  val ex = new RuntimeException("a grave error")
  val ex2 = new RuntimeException("not fatal")
  val ex3 = new RuntimeException("fatal error")
  val ex4 = new RuntimeException("another not fatal")

  val a = Ior2.right(2)
  val b = a.map(x => x * 4)
  val c = b.flatMap(_ => Ior2.right[Throwable, String]("a string"))
  val d = c.flatMap(_ => Ior2.left[Throwable, String](ex))
  val e = d.map(x => x + "something")

  val both = Ior2.both(ex2, 21)
  val both1 = both.map(x => x * 2)
  val both2 = both.flatMap(_ => Ior2.left[Throwable, Int](ex3))
  val both3 = both.flatMap(_ => Ior2.right[Throwable, Int](480))
  val both4 = both.flatMap(x => Ior2.both[Throwable, Int](ex4, x * 3))

  /**
   * Test case of task 1A
   */
  "right, left, both" should "return the respective Ior2" in {
    assert(Ior2.right[Throwable, Int](23) === Right2[Throwable, Int](23))
    assert(Ior2.right[Throwable, String]("string test") === Right2[Throwable, String]("string test"))
    assert(Ior2.right[Throwable, Boolean](true) === Right2[Throwable, Boolean](true))
    assert(Ior2.left[Throwable, String](ex) === Left2[Throwable, String](ex))
    assert(Ior2.both[Throwable, Int](ex, 23) === Both2[Throwable, Int](ex, 23))
  }

  /**
   * Test case of task 1B
   */
  "unit" should "return Ior2[A]" in {
    assert(Ior2.unit[Throwable, Int](23) === Right2[Throwable, Int](23))
  }

  /**
   * Test case of task 1C + 1D
   */
  "a" should "return Right2(2)" in {
    assert(a === Right2[Throwable, Int](2))
  }
  "b" should "return Right2(8)" in {
    assert(b === Right2[Throwable, Int](8))
  }
  "c" should "return Right2(\"a string\")" in {
    assert(c === Right2[Throwable, String]("a string"))
  }
  "d" should "return Left2(java.lang.RuntimeException: a grave error)" in {
    assert(d === Ior2.left(ex))
  }
  "e" should "return Left2(java.lang.RuntimeException: a grave error)" in {
    assert(e === Left2(ex))
  }

  "both1" should "return Both2(java.lang.RuntimeException: not fatal,42)" in {
    assert(both1 === Both2(ex2, 42))
  }
  "both2" should "return Left2(java.lang.RuntimeException: fatal error)" in {
    assert(both2 === Left2(ex3))
  }
  "both3" should "return Both2(java.lang.RuntimeException: not fatal,480)" in {
    assert(both3 === Both2(ex2, 480))
  }
  "both4" should "return Both2(java.lang.RuntimeException: another not fatal,63)" in {
    assert(both4 === Both2(ex4, 63))
  }
}