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
   * @param card input int
   * @return array of int value, 2 elements for ace, 1 for other cases
   */
  def values(card: Int): Array[Int] = {
    if(card == 11) Array(1, 11)
    else if (card >= 2 && card <= 10) Array(card)
    else Array()
  }

}
