import org.scalatest.flatspec.AnyFlatSpec

class MainSpec extends AnyFlatSpec {
  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  /**
   * Test case of task 1
   */
  val arr: Array[Int] = Array(1,2,3,4,5)
  val arrExcept: Array[Int] = Array();

  "max" should "return the maximum value" in {
    assert(Main.max(arr) === 5)
    assertThrows[UnsupportedOperationException] {
      Main.max(arrExcept)
    }
  }

  "min" should "return the minimum value" in {
    assert(Main.min(arr) === 1)
    assertThrows[UnsupportedOperationException] {
      Main.min(arrExcept)
    }
  }
  "sum" should "return the sum value" in {
    assert(Main.sum(arr) === 15)
    assertThrows[UnsupportedOperationException] {
      Main.min(arrExcept)
    }
  }
  /**
   * Test case of task 2
   */
  "parse" should "return the respective int" in {
    assert(Main.parse("A") === 11)
    assert(Main.parse("J") === 10)
    assert(Main.parse("2") === 2)
    assert(Main.parse("1") === 0)
  }

  "parseAll" should "return the respective int array" in {
    assert(Main.parseAll(Array("A","J","C","4")) === Array(11, 10,0,4))
  }

  "values" should "return the respective int array" in {
    assert(Main.values(11) === Array(11, 1))
    assert(Main.values(10) === Array(10))
    assert(Main.values(2) === Array(2))
    assert(Main.values(0) === Array())
  }
  "determineHandValue" should "return the respective int array" in {
    assert(Main.determineHandValue(Main.max) (Array(11,10,5))=== 26)
    assert(Main.determineHandValue(Main.min) (Array(11,10,5))=== 16)
  }
  "isBust" should "return the respective int array" in {
    assert(Main.isBust(22) === true)
    assert(Main.isBust(21) === false)
    assert(Main.isBust(15) === false)
  }
  "optimisticF" should "return the respective int array" in {
    assert(Main.optimisticF(Array(11,10,5)) === 26)
    assert(Main.optimisticF(Array(11,11,5)) === 27)
  }
  "pessimisticF" should "return the respective int array" in {
    assert(Main.pessimisticF(Array(11,10,5)) === 16)
    assert(Main.pessimisticF(Array(11,11,5)) === 7)
  }
  "determineBestHandValue" should "return the respective int array" in {
    assert(Main.determineBestHandValue(Array(11,10,5)) === 16)
    assert(Main.determineBestHandValue(Array(10,2,2)) === 14)
  }
}
