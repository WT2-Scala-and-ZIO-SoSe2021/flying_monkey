import lib.{StdAudio, KSA}

object Main extends App {
  print("Hello World")

  val test = KSA.whiteNoise()
  KSA.loop(test)(StdAudio.play)
  // StdAudio.play(0.9)
}