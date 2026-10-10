//KELAS VEHICLE
open class Vehicle(
    val brand: String,
    val model: String,
    val year: Int,
    val price: Double
) {
    var isSold: Boolean = false
        private set

    open fun calculateTax(): Double = price * 0.10

    fun sell(): Boolean = if (!isSold) { isSold = true; true } else false

    fun isAvailable(): Boolean = !isSold

    open fun displayInfo(): String =
        """
        Brand          : $brand
        Model          : $model 
        Tahun          : $year
        Harga          : Rp${"%.0f".format(price)}
        Pajak          : Rp${"%.0f".format(calculateTax())}
        Status         : ${if (isSold) "Terjual" else "Tersedia"}
        """.trimIndent()
}

//Subclass: CAR
open class Car(
    brand: String, model: String, year: Int, price: Double,
    val numberOfDoors: Int, val fuelType: String
) : Vehicle(brand, model, year, price) {
    override fun calculateTax(): Double = price * 0.12
    override fun displayInfo(): String = super.displayInfo() + "\n" +
            """
        Jumlah Pintu   : $numberOfDoors
        Bahan Bakar    : $fuelType
        """.trimIndent()
}

//Subclass: MOTORCYCLES
class Motorcycle(
    brand: String, model: String, year: Int, price: Double,
    val engineCapacity: Int, val type: String
) : Vehicle(brand, model, year, price) {
    override fun calculateTax(): Double = price * 0.05
    override fun displayInfo(): String = super.displayInfo() + "\n" +
            """
        Kapasitas      : ${engineCapacity}cc
        Tipe Motor     : $type
        """.trimIndent()
}

//Subclass: TRUCK
class Truck(
    brand: String, model: String, year: Int, price: Double,
    numberOfDoors: Int, fuelType: String,
    val loadCapacity: Double, val numberOfAxles: Int
) : Car(brand, model, year, price, numberOfDoors, fuelType) {
    override fun calculateTax(): Double = price * 0.15
    override fun displayInfo(): String = super.displayInfo() + "\n" +
            """
        Kapasitas Muat : ${loadCapacity} Ton
        Jumlah Axle    : $numberOfAxles
        """.trimIndent()
}

//KELAS DEALERSHIP
class Dealership(val name: String) {
    private val vehicles = mutableListOf<Vehicle>()

    fun addVehicle(vehicle: Vehicle) { vehicles.add(vehicle) }

    fun findVehicle(brand: String, model: String): Vehicle? =
        vehicles.find { it.brand.equals(brand, ignoreCase = true) && it.model.equals(model, ignoreCase = true) }

    fun sellVehicle(
        brand: String,
        model: String
    ): Boolean {

        val vehicle = findVehicle(brand, model)

        return if (vehicle != null && vehicle.sell()) {
            println("${vehicle.brand} ${vehicle.model} berhasil terjual")
            true
        } else {
            println("$brand $model tidak tersedia atau sudah terjual")
            false
        }
    }

    fun getAvailableVehicles(): List<Vehicle> = vehicles.filter { it.isAvailable() }

    fun getSoldVehicles(): List<Vehicle> = vehicles.filter { it.isSold }

    fun displayAllVehicles() {
        println("======= Kendaraan $name =======")
        vehicles.forEach {
            println(it.displayInfo())
            println("------------------------------------------------")
        }
    }

    fun displayAvailableVehicles() {
        println("\n======== KENDARAAN TERSEDIA ========")
        getAvailableVehicles().forEach {
            println(it.displayInfo())
            println("------------------------------------------")
        }
    }

    fun getTotalRevenue(): Double = getSoldVehicles().sumOf { it.price }
}

//Fungsi main()
fun main() {
    val dealer = Dealership("Dealer Motor Jaya")

    // Tambah 6 kendaraan (2 Mobil, 2 Motor, 2 Truk)
    dealer.addVehicle(Car("Toyota", "Avanza", 2022, 250_000_000.0, 5, "Bensin"))
    dealer.addVehicle(Car("Hyundai", "Ioniq 5", 2023, 750_000_000.0, 5, "Listrik"))
    dealer.addVehicle(Motorcycle("Honda", "CBR150R", 2021, 37_000_000.0, 150, "Sport"))
    dealer.addVehicle(Motorcycle("Yamaha", "NMAX", 2023, 32_000_000.0, 155, "Matic"))
    dealer.addVehicle(Truck("Isuzu", "Elf", 2020, 400_000_000.0, 2, "Diesel", 5.0, 2))
    dealer.addVehicle(Truck("Hino", "Ranger", 2019, 850_000_000.0, 2, "Diesel", 12.0, 3))

    //Menampilkan semua kendaraan
    dealer.displayAllVehicles()

    //Menampilkan kendaraan tersedia
    dealer.displayAvailableVehicles()

    //Melakukan penjualan
    println("\n---Penjualan---")
    dealer.sellVehicle("Toyota", "Avanza")
    dealer.sellVehicle("Yamaha", "NMAX")
    dealer.sellVehicle("Hino", "Ranger")

    //Menampilkan sisa kendaraan dan total pendapatan
    dealer.displayAvailableVehicles()

    println("\n======== TOTAL PENDAPATAN ========")
    println("Total Pendapatan: Rp${"%.0f".format(dealer.getTotalRevenue())}")
}
