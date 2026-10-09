class Temperature {
    //Celcius dengan private setter
    var celsius: Double = 0.0
        private set

    //Dihitung dari celcius
    val fahrenheit: Double
        get() = (celsius * 9.0 / 5.0) + 32.0

    //Dihitung dari kelvin
    val kelvin: Double
        get() = celsius + 273.15

    //Mengatur suhu Celcius dengan validasi >= -272.15
    fun setCelsius(value: Double) {
        if (value < -273.15) {
            println("Error: Suhu Celcius tidak boleh kurang dari -273.15°C!")
        } else {
            celsius = value
        }
    }

    fun setFahrenheit(value: Double) {
        val convertedCelsius = (value - 32.0) * 5.0 / 9.0
        setCelsius(convertedCelsius)
    }

    fun setKelvin(value: Double) {
        val convertedCelsius = value - 273.15
        setCelsius(convertedCelsius)
    }

    fun display() {
        println("=========SUHU=========")
        println("Celsius    : " + String.format("%.2f", celsius) + " °C")
        println("Fahrenheit : " + String.format("%.2f", fahrenheit) + " °F")
        println("Kelvin     : " + String.format("%.2f", kelvin) + " °K")
        println("----------------------")
    }
}
fun main() {
    val temp = Temperature()

    println("1. Set suhu ke 100°C: ")
    temp.setCelsius(100.0)
    temp.display()

    println("\n2. Set suhu ke 212°F: ")
    temp.setFahrenheit(212.0)
    temp.display()

    println("\n3.Set suhu ke 0 K°: ")
    temp.setKelvin(0.0)
    temp.display()

    println("\n4. Uji validasi batas suhu (dibawah -273.15°C): ")
    temp.setCelsius(-300.0)
}
