package object temperature {
    type Temperature = Double
    object Locale extends Enumeration {
        type Locale = String
        val US = "US"
        val SCI = "SCI"
        val Other = "Other"
    }
    implicit val locale: Locale.Locale = Locale.Other

    implicit class TemperatureClass(temp: Temperature) {
        val freezingPoint = 0.0
        val absoluteZero = -273.15

        def fahrenheit: Temperature = temp - 32 * 1.8

        def kelvin: Temperature = temp + absoluteZero

        def celsius: Temperature = temp

        def avg(other: Temperature): Temperature = (temp + other) / 2
    }

    def display(temperature: Temperature)(implicit locale: Locale.Locale) = locale match {
        case Locale.US => temperature.celsius + "°C"
        case Locale.SCI => temperature.fahrenheit + "°F"
        case Locale.Other => temperature.kelvin + "°K"
        case _ => "Locale can only be one of US or SCI  or Other"
    }

}