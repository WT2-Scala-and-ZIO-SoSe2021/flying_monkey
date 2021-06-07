package exercise3
import temperature._

object temperatureTest extends App{
    implicit val locale: Locale.Locale = Locale.US
    val t1 = 60.455.fahrenheit
    val t2 = 5.8.celsius
    println(display(t1))
    println(display(t2))
    println(display(t1 avg t2))

}
