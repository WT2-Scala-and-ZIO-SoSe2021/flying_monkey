package exercise2

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Success

class StackSpec extends AnyFlatSpec {
  /**
   * Test case of task 2A
   */
  val stack = Stack(1, Stack(2, Stack(3, StackEmpty())))
  val emptyStack = StackEmpty[Int]()

  "push" should "push a new element" in {
    assert(emptyStack.push(1) === Stack(1, StackEmpty()))
    assert(stack.push(4) === Stack(4, Stack(1, Stack(2, Stack(3, StackEmpty())))))
  }

  "pop" should "get one element from stack" in {
    assert(emptyStack.pop() === Success(StackEmpty()))
    assert(stack.pop().get === Stack(2, Stack(3, StackEmpty())))
  }

  "top" should "return the first head value" in {
    assert(emptyStack.top().isEmpty)
    assert(stack.top().get === 1)
  }

  "isEmpty" should "return boolean for stack" in {
    assert(emptyStack.isEmpty === true)
    assert(stack.isEmpty === false)
  }

  "reverse" should "reverse the whole stack" in {
    assert(emptyStack.reverse() === StackEmpty())
    assert(stack.reverse() === Stack(3, Stack(2, Stack(1, StackEmpty()))))
  }
}
