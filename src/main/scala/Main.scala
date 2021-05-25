import lib.{StdAudio, KSA}

object Main extends App {
  print("Hello World")

  val test = KSA.whiteNoise(199)
  // KSA.loop(test)(StdAudio.play)
  print(test)
}