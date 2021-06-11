package exercise3

import org.scalatest.flatspec.AnyFlatSpec
import temperature._

class TemperatureSpec extends AnyFlatSpec {
  val temp: Temperature = 100
  val avg = 10.0.fahrenheit avg 10.0.celsius

  /**
   * Test case of task 1B
   */
  "celsius, kelvin and fahrenheit" should "convert the temperature" in {
    assert(temp.celsius === 100)
    assert(temp.kelvin === 373.15)
    assert(temp.fahrenheit === 212)
  }

  /**
   * Test case of task 1C
   */
  "avg" should "calculate the average number of two temperatures" in {
    assert(avg === -18.8)
  }

  /**
   * Test case of task 1D
   */
  "display" should "display the temperature with local setting" in {
    assert(display(temp) === "100 °C")
  }
}