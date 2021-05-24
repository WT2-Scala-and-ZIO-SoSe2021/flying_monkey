package exercise2

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Success

class StackSpec extends AnyFlatSpec {
  /**
   * Test case of task 2A
   */
  val stack = StackCons(1, StackCons(2, StackCons(3, StackEmpty())))
  val emptyStack = StackEmpty()

  "push" should "push a new element" in {
    assert(StackEmpty().push(1) === StackCons(1, StackEmpty()))
    assert(StackCons(1, StackEmpty()).push(2) === StackCons(2, StackCons(1, StackEmpty())))
    // assert(emptyStack.push(1) === StackCons(1, StackEmpty()))
  }

  "pop" should "get one element from stack" in {
    assert(emptyStack.pop() === Success(StackEmpty()))
    assert(stack.pop().get === StackCons(2, StackCons(3, StackEmpty())))
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
    assert(stack.reverse() === StackCons(3, StackCons(2, StackCons(1, StackEmpty()))))
  }
}
