import org.scalatest.flatspec.AnyFlatSpec

class Ex1Spec extends AnyFlatSpec {
  /**
   * Test case of task 1
   */
  val arr: Array[Int] = Array(1,2,3,4,5)
  val arrExcept: Array[Int] = Array();

  "max" should "return the maximum value" in {
    assert(Ex1.max(arr) === 5)
    assertThrows[UnsupportedOperationException] {
      Ex1.max(arrExcept)
    }
  }

  "min" should "return the minimum value" in {
    assert(Ex1.min(arr) === 1)
    assertThrows[UnsupportedOperationException] {
      Ex1.min(arrExcept)
    }
  }
  "sum" should "return the sum value" in {
    assert(Ex1.sum(arr) === 15)
    assertThrows[UnsupportedOperationException] {
      Ex1.sum(arrExcept)
    }
  }
  /**
   * Test case of task 2
   */
  "parse" should "return the respective int" in {
    assert(Ex1.parse("A") === 11)
    assert(Ex1.parse("J") === 10)
    assert(Ex1.parse("2") === 2)
    assert(Ex1.parse("1") === 0)
  }

  "parseAll" should "return the respective int array" in {
    assert(Ex1.parseAll(Array("A","J","C","4")) === Array(11, 10,0,4))
  }

  "values" should "return the respective int array" in {
    assert(Ex1.values(11) === Array(11, 1))
    assert(Ex1.values(10) === Array(10))
    assert(Ex1.values(2) === Array(2))
    assert(Ex1.values(0) === Array())
  }
  "determineHandValue" should "return the respective int array" in {
    assert(Ex1.determineHandValue(Ex1.max) (Array(11,10,5))=== 26)
    assert(Ex1.determineHandValue(Ex1.min) (Array(11,10,5))=== 16)
  }
  "isBust" should "return the respective int array" in {
    assert(Ex1.isBust(22) === true)
    assert(Ex1.isBust(21) === false)
    assert(Ex1.isBust(15) === false)
  }
  "optimisticF" should "return the respective int array" in {
    assert(Ex1.optimisticF(Array(11,10,5)) === 26)
    assert(Ex1.optimisticF(Array(11,11,5)) === 27)
  }
  "pessimisticF" should "return the respective int array" in {
    assert(Ex1.pessimisticF(Array(11,10,5)) === 16)
    assert(Ex1.pessimisticF(Array(11,11,5)) === 7)
  }
  "determineBestHandValue" should "return the respective int array" in {
    assert(Ex1.determineBestHandValue(Array(11,10,5)) === 16)
    assert(Ex1.determineBestHandValue(Array(10,2,2)) === 14)
  }
}
