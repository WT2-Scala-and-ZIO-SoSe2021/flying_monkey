object TwentyOne {
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
}
