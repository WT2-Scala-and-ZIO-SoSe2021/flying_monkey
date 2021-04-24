import org.scalatest.flatspec.AnyFlatSpec

class TwentyOneSpec extends AnyFlatSpec {
  /**
   * Test case of task 2A
   */
  "parse" should "return the respective int" in {
    assert(TwentyOne.parse("Q") === 10)
  }

  "parseAll" should "return the respective int array" in {
    assert(TwentyOne.parseAll(Array("3", "K", "A", "not a number")) === Array(3, 10, 11, 0))
  }

  /**
   * Test case of task 2B
   */
  "values" should "return the respective int array" in {
    assert(TwentyOne.values(11) === Array(1, 11))
    assert(TwentyOne.values(2) === Array(2))
    assert(TwentyOne.values(99) === Array(0))
  }

  /**
   * Test case of task 2C
   * No need to write test case for determineHandValue() as strategy is just a specification of a function
   */
  "isBust" should "return true or false" in {
    assert(TwentyOne.isBust(23) === true)
    assert(TwentyOne.isBust(21) === false)
    assert(TwentyOne.isBust(-2) === false)
  }

  /**
   * Test case of task 2D
   * No need to write test case for determineHandValue() as strategy is just a specification of a function
   */
  "optimisticF" should "maximum sum value of hand" in {
    assert(TwentyOne.optimisticF(Array(11, 2, 0)) === 13)
    assert(TwentyOne.optimisticF(Array(3, 4, 5)) === 12)
  }

  "pessimisticF" should "minimum sum value of hand" in {
    assert(TwentyOne.pessimisticF(Array(11, 2, 0)) === 3)
    assert(TwentyOne.pessimisticF(Array(3, 4, 5)) === 12)
  }

  /**
   * Test case of task 2E
   */
  "determineBestHandValue" should "return best hand value" in {
    assert(TwentyOne.determineBestHandValue(Array(11, 2, 0)) === 13)
    assert(TwentyOne.determineBestHandValue(Array(11, 10, 10)) === 21)
  }
}
