package exercise3

import exercise3.ZioApp._
import zio.{ZIO, random}
import zio.test.DefaultRunnableSpec
import zio.test.environment.TestRandom
import zio.test.Assertion._
import zio.test.assert

object ZioAppSpec extends DefaultRunnableSpec {
  def spec = suite("ZioAppSpec")(
    testM("return failure if frequency or volume out of bound") {
      for {
        // failure <- whiteNoise(-440)
        result <- ZIO.fail("wrong input")
      } yield assert(result)(fails(equalTo("wrong input")))
    },
    testM("queue must be between -0.5 and 0.5") {
      for {
        queue <- whiteNoise()
      } yield assert(queue.front.get)(equalTo(isLessThan(0.5) && isGreaterThan(-0.5)))
    },
    testM("return a white noise list based on frequency") {
      for {
        _ <- TestRandom.feedInts(262, 392, 349, 330, 294)
        x <- random.nextIntBounded(440)
        queue <- whiteNoise(x)
      } yield assert(queue.front.get)(equalTo(262))
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
