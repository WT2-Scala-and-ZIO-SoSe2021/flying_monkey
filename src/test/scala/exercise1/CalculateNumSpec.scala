package exercise1

import exercise1.CalculateNum
import org.scalatest.flatspec.AnyFlatSpec

class CalculateNumSpec extends AnyFlatSpec {
  /**
   * Test case of task 1
   */
  val arr: Array[Int] = Array(1,2,3,4,5)
  val arrExcept: Array[Int] = Array();

  "max" should "return the maximum value" in {
    assert(CalculateNum.max(arr) === 5)
    assertThrows[UnsupportedOperationException] {
      CalculateNum.max(arrExcept)
    }
  }

  "min" should "return the minimum value" in {
    assert(CalculateNum.min(arr) === 1)
    assertThrows[UnsupportedOperationException] {
      CalculateNum.min(arrExcept)
    }
  }

  "sum" should "return the sum value" in {
    assert(CalculateNum.sum(arr) === 15)
    assert(CalculateNum.sum(arrExcept) === 0)
  }
}

