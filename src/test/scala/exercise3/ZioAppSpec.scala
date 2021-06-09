package exercise3

import exercise3.ZioApp._
import zio.random
import zio.test.DefaultRunnableSpec
import zio.test.environment.TestRandom
import zio.test.Assertion.equalTo
import zio.test.assert

object ZioAppSpec extends DefaultRunnableSpec {
  def spec = suite("ZioAppSpec")(
    testM("return a white noise list based on frequency") {
      for {
        _ <- TestRandom.feedInts(262, 392, 349, 330, 294)
        x <- random.nextIntBounded(440)
      } yield assert(
        whiteNoise(262)
      )(equalTo(whiteNoise(x)))
    },
    testM("return a white noise list based on volume") {
      for {
        _ <- TestRandom.feedDoubles(0.5, 0.6, 0.7)
        y <- random.nextDouble
      } yield assert(
        List(262, 0.5)
      )(equalTo(List(262, y)))
    }
  )
}
