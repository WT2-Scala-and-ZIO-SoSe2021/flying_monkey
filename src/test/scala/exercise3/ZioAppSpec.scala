package exercise3

import exercise3.ZioApp._
import zio.random
import zio.test.DefaultRunnableSpec
import zio.test.environment.TestRandom
import zio.test.Assertion.{equalTo, fails, isWithin}
import zio.test.assert

object ZioAppSpec extends DefaultRunnableSpec {
  def spec = suite("ZioAppSpec")(
    testM("return a white noise list based on frequency") {
      for {
        _ <- TestRandom.feedInts(262, 392, 349, 330, 294)
        x <- random.nextIntBounded(440)
        queue <- whiteNoise(x)
      } yield assert(
        queue.front()
      )(equalTo(Some(0.1983764484234759)))
    },

    testM("return a white noise list based on volume") {
      for {
        _ <- TestRandom.feedDoubles(0.5, 0.6, 0.7)
        y <- random.nextDouble
        queue <- whiteNoise(262, y)
      } yield assert(
        queue.front()
      )(equalTo(Some(0.3)))
    },

    testM("queue should be between -0.5 and 0.5") {
      for {
        queue <- whiteNoise()
      } yield assert(
        queue.front().get
      )(isWithin(-0.5, 0.5))
    },

    testM("queue should return failure if wrong parameter is given") {
      for {
        queue <- whiteNoise(-50).run
      } yield assert(queue)(fails(equalTo("wrong input")))
    }
  )
}
