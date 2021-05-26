import lib.{KSA, StdAudio}

import scala.util.Random

object Main extends App {
  val sound1 = KSA.whiteNoise(262)
  val sound2 = KSA.whiteNoise(392)
  val sound3 = KSA.whiteNoise(440)
  val sound4 = KSA.whiteNoise(349)
  val sound5 = KSA.whiteNoise(330)
  val sound6 = KSA.whiteNoise(294)

  // Twinkle Twinkle Little Star
  KSA.loop(sound1)(StdAudio.play)
  KSA.loop(sound1)(StdAudio.play)
  KSA.loop(sound2)(StdAudio.play)
  KSA.loop(sound2)(StdAudio.play)
  KSA.loop(sound3)(StdAudio.play)
  KSA.loop(sound3)(StdAudio.play)
  KSA.loop(sound2)(StdAudio.play)

  KSA.loop(sound4)(StdAudio.play)
  KSA.loop(sound4)(StdAudio.play)
  KSA.loop(sound5)(StdAudio.play)
  KSA.loop(sound5)(StdAudio.play)
  KSA.loop(sound6)(StdAudio.play)
  KSA.loop(sound6)(StdAudio.play)
  KSA.loop(sound1)(StdAudio.play)

  // val rand = new Random()
  // (1 to 25000).map(_ => (rand.nextDouble() + -.5) * 0.1).toArray.foreach(StdAudio.play)
}