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
    assert(TwentyOne.values(99) === Array())
  }
}
