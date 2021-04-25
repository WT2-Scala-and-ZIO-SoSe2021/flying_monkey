object TwentyOne {
  /********** TASK 2A **********/
  /**
   * Convert string to int
   * @param s input string
   * @return integer, none if exception occurs
   */
  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }

  /**
   * Parse string representing card into integer
   * @param str input string
   * @return integer based on str
   */
  def parse(str: String): Int = {
    val num = toInt(str)
    if(num.isDefined && num.get >= 2 && num.get <= 10) {
      num.get
    } else {
      if(str == "A") 11
      else if(str == "J" || str == "Q" || str == "K") 10
      else 0
    }
  }

  /**
   * Parse an array to card string to array of int
   * @param arr string array
   * @return int array
   */
  def parseAll(arr: Array[String]): Array[Int] = {
    arr.map(i => parse(i))
  }

  /********** TASK 2B **********/
  /**
   * Parse an int (represented card) to array of int (represented card value)
   * Special case should return Array(0) so max() and min() function wont return error
   * @param card input int
   * @return array of int value, 2 elements for ace, 1 for other cases
   */
  def values(card: Int): Array[Int] = {
    if(card == 11) Array(1, 11)
    else if (card >= 2 && card <= 10) Array(card)
    else Array(0)
  }

  /********** TASK 2C **********/
  /**
   * A higher order curried function - function that return a function as result
   * Ex: hand = Array(11, 2, 0) -> values() -> hand = Array(Array(11,1), Array(2), Array())
   * -> strategy() -> something -> sum() -> int
   * @param strategy just a function
   * @param hand int array of card values
   * @return CalculateNum.sum()
   */
  def determineHandValue(strategy: Array[Int] => Int)(hand: Array[Int]): Int = CalculateNum.sum(hand.map(values).map(strategy))

  /**
   * Check if the hand is busted
   * @param handSum the total values of the hand
   * @return boolean status (bust or not)
   */
  def isBust(handSum: Int): Boolean = {
    if(handSum > 21) true
    else false
  }

  /********** TASK 2D **********/
  /**
   * Apply strategy max
   * Ex: hand = Array(11, 2, 0) -> values() -> hand = Array(Array(11,1), Array(2), Array())
   * -> max() -> (11, 2, 0) -> sum() -> 13
   * @param hand int array of hand values
   * @return int sum of hand values
   */
  def optimisticF(hand: Array[Int]): Int = determineHandValue(CalculateNum.max)(hand)

  /**
   * Apply strategy min
   * Ex: hand = Array(11, 2, 0) -> values() -> hand = Array(Array(11,1), Array(2), Array())
   * -> min() -> (1, 2, 0) -> sum() -> 3
   * @param hand int array of hand values
   * @return int sum of hand values
   */
  def pessimisticF(hand: Array[Int]): Int = determineHandValue(CalculateNum.min)(hand)

  /********** TASK 2E **********/
  /**
   * Determine best hand value using max/min function
   * if max value is busted => return min value
   * @param hand int array of hand values
   * @return int sum of hand values
   */
  def determineBestHandValue(hand: Array[Int]): Int = {
    if (isBust(optimisticF(hand))) pessimisticF(hand)
    else optimisticF(hand)
  }

  /********** OPTIONAL TASK **********/
  /**
   * Determine best hand value
   * If there's more than one A, only maximum 1 A can be 11, 2 A (11) => 22 => bust
   * If the lowest hand is okay with increasing 10 more => can change one A (1) into A (11)
   * @param hand int array of hand values
   * @return int sum of hand values
   */
  def determineBestHandValue2(hand: Array[Int]): Int = {
    if (isBust(optimisticF(hand))) {
      if(pessimisticF(hand) <= 11 && hand.contains(11)) { // can change 1 to 11
        pessimisticF(hand) + 10
      } else if(pessimisticF(hand) > 21) { // even the lowest hand is bust, no saving there
        0
      } else pessimisticF(hand)
    }
    else optimisticF(hand)
  }
}
