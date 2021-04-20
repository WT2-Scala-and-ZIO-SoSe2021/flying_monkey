object Main extends App {
  /**
   * Maximum value of an integer array
   * @param arr input integer array
   * @return maximum value of arr
   */
  def max(arr: Array[Int]): Int = arr.reduce(_ max _)

  /**
   * Minimum value of an integer array
   * @param arr input integer array
   * @return minimum value of arr
   */
  def min(arr: Array[Int]): Int = arr.reduce(_ min _)

  /**
   * Sum of all elements in an integer array
   * @param arr input integer array
   * @return sum of all integer elements, 0 if array is empty
   */
  def sum(arr: Array[Int]): Int = arr.fold(0) (_ + _)

}