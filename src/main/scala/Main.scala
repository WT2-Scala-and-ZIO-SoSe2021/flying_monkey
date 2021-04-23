object Main extends App {
  print("Hello World")
  // find the minimum element using reduce function
  def min( Seq: Array[Int]):Int = Seq.reduce(_ min _)

  // find the maximum element using reduce function
  def max( Seq: Array[Int]):Int = Seq.reduce(_ max _)

  // find the sum of the elements
  // using reduce function
  def sum( Seq: Array[Int]):Int = Seq.reduce((a, b) => a + b)

  //task 2A
  def parse( card: String ) : Int = {
    if(card=="A") 11
    else if (card=="J" || card=="Q" || card=="K") 10
    else if (card=="2" || card=="3" || card=="4"
      || card=="5"|| card=="6"|| card=="7"
      || card=="8"|| card=="9") card.toInt else 0

  }
  def parseAll( cards : Array[String] ) : Array[Int]  = cards.map(card => parse(card))


  //task 2Bs
  def values( card : Int ) : Array[Int]  = {
    if(card==11) Array(11,1)
    else  if (card >= 2 && card <= 10) Array(card)
    else Array()
  }
  //task 2C
  def determineHandValue(strategy: Array[Int] => Int)(hand: Array[Int]): Int = {
    val value = hand.map(x => values(x))
    val handValue = value.map(x => strategy(x))
    sum(handValue)
  }
  def isBust(handValue: Int): Boolean = handValue > 21

  //task 2D
  def optimisticF(hand: Array[Int]): Int = determineHandValue(max) (hand)

  def pessimisticF(hand: Array[Int]): Int = determineHandValue(min) (hand)

  //task 2E
  def determineBestHandValue(hand: Array[Int]): Int = {
    val handValue = optimisticF(hand)
    if (isBust(handValue)) pessimisticF(hand) else handValue
  }
}