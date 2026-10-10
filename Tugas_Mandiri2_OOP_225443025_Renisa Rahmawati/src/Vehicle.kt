open class Vehicle(
    val plateNumber: String,
    val brand: String,
    val model: String,
    val year: Int,
    val isAvailable: Boolean = true
) {
    open fun getType(): String {
        return "Kendaraan Umum"
    }

    open fun calculateFare(distanceKm: Double): Double {
        return 5000.0 + (distanceKm * 2000.0)
    }

    open fun displayInfo() {
        println("Jenis      : ${getType()}")
        println("Plat Nomor : $plateNumber")
        println("Brand      : $brand")
        println("Model      : $model")
        println("Tahun      : $year")
        println("Status     : ${if (isAvailable) "Tersedia" else "Tidak Tersedia"}")
    }
}