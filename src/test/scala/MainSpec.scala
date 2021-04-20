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
    assert(Main.sum(arrExcept) === 0)
  }
}
