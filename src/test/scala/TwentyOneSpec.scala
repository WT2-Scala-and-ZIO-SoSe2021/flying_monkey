import org.scalatest.flatspec.AnyFlatSpec

class TwentyOneSpec extends AnyFlatSpec {
  val str = "Q"
  val strArr: Array[String] = Array("3", "K", "A", "not a number")

  /**
   * Test case of task 2A
   */
  "parse" should "return the respective int" in {
    assert(TwentyOne.parse(str) === 10)
  }

  "parseAll" should "return the respective int array" in {
    assert(TwentyOne.parseAll(strArr) === Array(3, 10, 11, 0))
  }
}
